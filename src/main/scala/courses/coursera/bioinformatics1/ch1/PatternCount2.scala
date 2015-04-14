package courses.coursera.bioinformatics1.ch1

import scala.io.Source

object PatternCount2 extends App {

	val path = "/Users/hieronymus/Development/Workspace_BAK/ScalaLab3/src/main/data/"
	val docName = "data2.txt"
	
	val source = Source.fromFile(path + docName)
	val text = source.getLines.mkString
	source.close // should be in finally
	
	def pattPos(pattern: String, str: String): List[Int] = {
		def inter(index: Int, indices: List[Int]): List[Int] = {
			val found = str.indexOf(pattern, index)
			if (found < 0) indices // none found, we're done
			else inter(found + 1, indices ++ List(found))
		}
		inter(0, List.empty[Int])
	}
	
	val pattern = "CCGGTAGCC"
	val result = pattPos(pattern , text)
	println("pattern locations: " + result)
	println("number of locations: " + result.length)
}







