package lab.nlp.vectorspacemodel.tfidf

import common.Path._
import scala.io.Source
import java.io.File
import math._

/*
 * I am, somewhat paradocically, using Maps instead of Matrices as my 
 * mechanism for tallying, counting, and looking up frequency values.
 * This is an admitedly paradoxical early stage convience for developing 
 * my intuition for Semantic Vectory Models.
 */
import scala.collection.mutable.Map

object TfIdfService_5 {
        
    // Holds count of number of docs each term appears in.
    val docsPerTerm = Map.empty[String, Int]
    
    // ----- Methods ----------------- //
    
    def tfidf(corpus: Array[File], stopwords: List[String])
        :List[(String, List[(String, Double)])] = {
        // Array of term counts per doc.
        val termCountsPerDoc = for(file <- corpus) yield mapTerms(file, stopwords)
    
        // Calculate term frequency for each term in each doc
        val tfsPerDoc = termCountsPerDoc.map{
            case (title, termCounts) => {
                
                // Tally number of docs each term appears in (side effect)
                termCounts.foreach{
                    case (term, _) => docsPerTerm.get(term) match {
                        case Some(_) => docsPerTerm(term) += 1
                        case None => docsPerTerm(term) = 1
                    }
                }
            
                // counts, frequency ratio (normalized)
                val maxFreq = (termCounts.values.max).toFloat
                val freqs = termCounts.map{
                    case (term, count) => (term, count / maxFreq)
                }
                val freqsList = freqs.toList.sortBy(_._2).reverse
                (title, freqsList)
            } // end case
        } // END termDocMaps.map
        
        // Calculate TFIDF (culmination)
        // Sort remains by TF to highlight effect of IDF
        val allTFIDFs = tfsPerDoc.map{
            case (title, terms) => {
                val tfidfs = terms.map{
                    case (term, tf) => {
                        val idf = log2(docsPerTerm.size.toFloat / docsPerTerm(term))
                        (term, tf * idf)
                    }
                }
                (title, tfidfs)
            }
        } // end allTFIDFs
        
        // Display output of all TFIDF scores per doc
        /*allTFIDFs foreach {
            doc => {
                println(doc._1)
                println(doc._2.take(10))
                println()
            }
        }*/
        
        allTFIDFs.toList // return result
    } // END tdidf
    
        // Parse file, filter, count terms. Returns tuple of (file name, term counts)
    def mapTerms(file: File, stopwords: List[String])
        :(String, Map[String, Int]) = {
        val doc = Source.fromFile(file)
        val terms = doc.getLines.flatMap(preprocess(_))
            .filter(term => !stopwords.contains(term) && term != "")
            
        // Holds term counts for this doc. Mutable (side effect)
        val termCounts = Map.empty[String, Int]
        // Count number of times each term occurs in this doc.
        terms.foreach {
            term => termCounts.get(term) match {
                case Some(_) => termCounts(term) += 1
                case None => termCounts(term) = 1
            }
        }
        
        (file.getName, termCounts)
    } // end mapTerms
    
    def preprocess(str: String): Array[String] = {
        val splits = str.split("[ !,.:;]+").map(_.toLowerCase)
        splits.map { s => s.replaceAll("(?m)^[ \t]*\r?\n", "") }
    }
    
    // Display term frequency scores for each doc
    /*tfCounts foreach {
        doc => {
            println(doc._1); // doc name
            println(doc._2.take(20)) // term frequencies
            println
        }
    }*/
    
    // log2 function
    def log2(x: Double) = scala.math.log(x)/scala.math.log(2)
}

