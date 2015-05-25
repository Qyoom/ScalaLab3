package lab.tabulate

import scala.collection._

object Tabulate_lab_1 {
  val doc = mutable.ArrayBuffer.tabulate(20)(i => s"Page $i, ")
                                                  //> doc  : scala.collection.mutable.ArrayBuffer[String] = ArrayBuffer("Page 0, "
                                                  //| , "Page 1, ", "Page 2, ", "Page 3, ", "Page 4, ", "Page 5, ", "Page 6, ", "P
                                                  //| age 7, ", "Page 8, ", "Page 9, ", "Page 10, ", "Page 11, ", "Page 12, ", "Pa
                                                  //| ge 13, ", "Page 14, ", "Page 15, ", "Page 16, ", "Page 17, ", "Page 18, ", "
                                                  //| Page 19, ")
}