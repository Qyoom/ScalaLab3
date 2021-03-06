package courses.coursera.bioinformatics1.ch1

object PatternPositions_lab_1 {

	def pattPos(pattern: String, str: String): List[Int] = {
		def inter(index: Int, indices: List[Int]): List[Int] = {
			val found = str.indexOf(pattern, index)
			if (found < 0) indices // none found, we're done
			else inter(found + 1, indices ++ List(found))
		}
		inter(0, List.empty[Int])
	}                                         //> pattPos: (pattern: String, str: String)List[Int]
	
	pattPos("ATAT", "GATATATGCATATACTT")      //> res0: List[Int] = List(1, 3, 9)
	
}