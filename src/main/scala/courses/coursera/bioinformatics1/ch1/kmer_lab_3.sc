package courses.coursera.bioinformatics1.ch1

object kmer_lab_3 {

	// Finds character segment of length k with the highest frequency in the text.
	def highestFreqSeq(text: String, k: Int) = {
		require(k < text.length)
		
		// prepare kmers
		val kSegments = text.toLowerCase.iterator.sliding(k).map(
			segment => segment.flatMap(_.toString).mkString
		).toList
		
		// Index kmers in Dictionary, recursive
		def indexKmers(
			kSegments: List[String], kmerStore: Map[String, Int]
		): Map[String, Int] = {
			kSegments match {
				case Nil => kmerStore
				case kmer :: tail => {
					if(kmerStore.contains(kmer)) {
			  			indexKmers(kSegments.tail, kmerStore + (kmer -> (kmerStore(kmer) + 1)))
			  		}
			  		else {
			  			indexKmers(kSegments.tail, kmerStore + (kmer -> 1))
			  		}
		  		} // end case kmer :: tail
	  		} // end kSegments match
		} // end indexKmers
		
		// Start
		val kmerStore = indexKmers(kSegments, Map.empty[String, Int])
		
		// Invert the Map
		// http://daily-scala.blogspot.com/2010/03/how-to-reverse-map.html
		val inverseStore = kmerStore groupBy {_._2} map {
			case (count, kmer_counts) => (count, kmer_counts.unzip._1)
		}
		
		// Sort Map
		// https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s23.html
		import scala.collection.immutable.ListMap
		ListMap(inverseStore.toSeq.sortWith(_._1 > _._1):_*)
		
	} //                                      //> highestFreqSeq: (text: String, k: Int)scala.collection.immutable.ListMap[In
                                                  //| t,scala.collection.immutable.Iterable[String]]
	
	val text2 = "TheRainInSpainFallsMainlyOnThePlainOfTheCountryWhereTheyLive"
                                                  //> text2  : String = TheRainInSpainFallsMainlyOnThePlainOfTheCountryWhereTheyL
                                                  //| ive
	highestFreqSeq(text2, 3).take(3)          //> res0: scala.collection.immutable.ListMap[Int,scala.collection.immutable.Ite
                                                  //| rable[String]] = Map(4 -> List(ain, the), 2 -> List(her), 1 -> List(ino, pa
                                                  //| i, try, nly, ret, ini, all, inl, eth, oun, nof, yon, lyo, hep, ryw, ins, nt
                                                  //| r, lsm, epl, inf, liv, eyl, hec, nth, sma, whe, lls, eco, fth, mai, pla, ra
                                                  //| i, era, cou, unt, spa, ere, lai, ywh, nin, fal, nfa, yli, hey, ive, oft, on
                                                  //| t, nsp))
}
/*



 




















*/