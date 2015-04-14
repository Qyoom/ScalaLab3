package courses.coursera.bioinformatics1.ch1

import scala.io.Source
import courses.coursera.bioinformatics1.ch1.KmerFrequency.highestFreqSeq

object HighestFreq_io {
	
	val path = "/Users/hieronymus/Development/Workspace_BAK/ScalaLab3/src/main/data/"
                                                  //> path  : String = /Users/hieronymus/Development/Workspace_BAK/ScalaLab3/src/m
                                                  //| ain/data/
	val docName = "data2.txt"                 //> docName  : String = data2.txt
	
	val source = Source.fromFile(path + docName)
                                                  //> source  : scala.io.BufferedSource = non-empty iterator
	val text = source.getLines.mkString       //> text  : String = GCACGAAGGTATGGCAGTTGGCAGTTGGCGTATGGCGCGTCCTGAAGGGAGAGCACGAA
                                                  //| GAGTTGGCAGTTGGCGCGTCCTGAAGGGAGAGAAGGGAGAGCGTCCTGCACGAAGGCGTCCTAGTTGGCAGTTGGC
                                                  //| GCACGAAGAGTTGGCAGTTGGCGCGTCCTGCACGAAGGTATGGCAGTTGGCGCACGAAGGCGTCCTAGTTGGCGTA
                                                  //| TGGCGCGTCCTGAAGGGAGAGCGTCCTGAAGGGAGAGCACGAAGGCACGAAGGCACGAAGGAAGGGAGAGTATGGC
                                                  //| GCACGAAGGCACGAAGAGTTGGCGCGTCCTGCACGAAGGAAGGGAGAGCACGAAGAGTTGGCGCACGAAGGCGTCC
                                                  //| TGCACGAAGGCACGAAGGCGTCCTAGTTGGCGCGTCCTGAAGGGAGAGTATGGCGTATGGCGTATGGCGAAGGGAG
                                                  //| AGAAGGGAGAGCGTCCTGCGTCCTGCACGAAGGCACGAAGGAAGGGAGAGTATGGCAGTTGGCGCACGAAGGAAGG
                                                  //| GAGAAGTTGGCGTATGGCGTATGGCGAAGGGAGAGCACGAAGGCGTCCTGCGTCCTGAAGGGAGAGAAGGGAGAGC
                                                  //| ACGAAGGTATGGCAGTTGGCGTATGGCGCGTCCTAGTTGGCAGTTGGCGCGTCCTGAAGGGAGAGAAGGGAGAGCG
                                                  //| TCCTGCACGAAGGCGTCCTGAAGGGAGAGAAGGGAGAGTATGGCGAAGGGAGAGCGTCCTGCGTCCTGAAGGGAGA
                                                  //| GCGTCCTGCGTCCTAGTTGGCGTATGGCGCACGAAGGTATGGCGTATGGCAGTTGGCGTATGGCGCGTCCTGAAGG
                                                  //| GAGAGCGTCCTGCGTCCTGTATGGCGCGTCCT
	source.close
	val k = 11                                //> k  : Int = 11
	highestFreqSeq(text, k).head._1           //> res0: Int = 12
	highestFreqSeq(text, k).head._2.mkString(" ")
                                                  //> res1: String = GAAGGGAGAGC
}
/*



*/