package books.funproginscala.ch3

object List_client_1 {
  import books.funproginscala.ch3.List._
  
  val l1 = List(1,2,3,4)                          //> l1  : books.funproginscala.ch3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Nil))
                                                  //| ))
  val l2 = List("ape", "apple", "banana", "app")  //> l2  : books.funproginscala.ch3.List[String] = Cons(ape,Cons(apple,Cons(banan
                                                  //| a,Cons(app,Nil))))
  val l3 = List(5, 6, 7)                          //> l3  : books.funproginscala.ch3.List[Int] = Cons(5,Cons(6,Cons(7,Nil)))
 
  val l4 = List(1d,2,3,4)                         //> l4  : books.funproginscala.ch3.List[Double] = Cons(1.0,Cons(2.0,Cons(3.0,Con
                                                  //| s(4.0,Nil))))
  
  val l5 = Cons(0d, l4)                           //> l5  : books.funproginscala.ch3.Cons[Double] = Cons(0.0,Cons(1.0,Cons(2.0,Con
                                                  //| s(3.0,Cons(4.0,Nil)))))
  
  val l6 = List("zebra", "zygote")                //> l6  : books.funproginscala.ch3.List[String] = Cons(zebra,Cons(zygote,Nil))
  val res01 = sum(l1)                             //> res01  : Int = 10
  val res02 = sumAlt1(l1)                         //> res02  : Int = 10
  val res03 = sumAlt2(l1)                         //> res03  : Int = 10
  val resA3 = sumAlt3(l1)                         //> resA3  : Int = 10
  val resA4 = sumAlt4(l1)                         //> resA4  : Int = 10
  val resA5 = sumAlt5(l1)                         //> resA5  : Int = 10
  
  val res04 = product(l4)                         //> res04  : Double = 24.0
  val res05 = prodAlt1(l4)                        //> res05  : Double = 24.0
  val res06 = prodAlt1(Nil)                       //> res06  : Double = 1.0
  val res07 = prodAlt1(l5)                        //> res07  : Double = 0.0
  val res08 = prodAlt2(l5)                        //> res08  : Double = 0.0
  val res09 = prodAlt2(l4)                        //> res09  : Double = 24.0
  val resA1 = prodAlt3(l4)                        //> resA1  : Double = 24.0
  val resA2 = prodAlt4(l4)                        //> resA2  : Double = 24.0
  
  val res011 = tail(l1)                           //> res011  : books.funproginscala.ch3.List[Int] = Cons(2,Cons(3,Cons(4,Nil)))
  //tail(List()) // correctly throws error
  setHead("fee", List("foo", "fi", "fo", "fum"))  //> res0: books.funproginscala.ch3.List[String] = Cons(fee,Cons(fi,Cons(fo,Cons(
                                                  //| fum,Nil))))
  //setHead("fee", List()) // correctly throws error
  val res0 = drop(l1, 0)                          //> res0  : books.funproginscala.ch3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Nil
                                                  //| ))))
  
  val res1 = drop(l1, 1)                          //> res1  : books.funproginscala.ch3.List[Int] = Cons(2,Cons(3,Cons(4,Nil)))
  val res2 = drop(l1, 2)                          //> res2  : books.funproginscala.ch3.List[Int] = Cons(3,Cons(4,Nil))
  val res3 = drop(l1, 3)                          //> res3  : books.funproginscala.ch3.List[Int] = Cons(4,Nil)
  val res4 = drop(l1, 4)                          //> res4  : books.funproginscala.ch3.List[Int] = Nil
 
  val res5 = drop(l1, 5)                          //> res5  : books.funproginscala.ch3.List[Int] = Nil
  
  val res6a = dropWhile(l2, (x:String) => x.length < 4)
                                                  //> res6a  : books.funproginscala.ch3.List[String] = Cons(apple,Cons(banana,Con
                                                  //| s(app,Nil)))
  // curried
  val res6b = dropWhileCur(l2) (x => x.length < 4)//> res6b  : books.funproginscala.ch3.List[String] = Cons(apple,Cons(banana,Con
                                                  //| s(app,Nil)))
  
  // ERROR
  //val res6c = dropWhileCurAlt(x => x.length < 4)(l2)
  val res7 = dropWhile(l2, (x:String) => x.startsWith("a"))
                                                  //> res7  : books.funproginscala.ch3.List[String] = Cons(banana,Cons(app,Nil))
  val res8a = append(l1, l3)                      //> res8a  : books.funproginscala.ch3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,C
                                                  //| ons(5,Cons(6,Cons(7,Nil)))))))
  
  val res8a1 = sum(res8a)                         //> res8a1  : Int = 28
  
  val res8b = appendViaFR(l1, l3)                 //> res8b  : books.funproginscala.ch3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,C
                                                  //| ons(5,Cons(6,Cons(7,Nil)))))))
  // With TR, this is not correct!
  val res8c = appendViaFRTR(l1, l3)               //> res8c  : books.funproginscala.ch3.List[Int] = Cons(4,Cons(3,Cons(2,Cons(1,C
                                                  //| ons(5,Cons(6,Cons(7,Nil)))))))
  val res9 = init(l1)                             //> res9  : books.funproginscala.ch3.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
  val res10 = init(List(1))                       //> res10  : books.funproginscala.ch3.List[Int] = Nil
  //val res11 = init(List()) // correctly throws error
  val res12 = init(List(1,2))                     //> res12  : books.funproginscala.ch3.List[Int] = Cons(1,Nil)
  
  val res13 = initAlt(l1)                         //> res13  : books.funproginscala.ch3.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
  val res14 = initAlt(List(1))                    //> res14  : books.funproginscala.ch3.List[Int] = Nil
  val res15 = initAlt(List(1,2))                  //> res15  : books.funproginscala.ch3.List[Int] = Cons(1,Nil)
  val res16 = foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_))
                                                  //> res16  : books.funproginscala.ch3.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
  val res17 = length(l2)                          //> res17  : Int = 4
  val res18 = length(l5)                          //> res18  : Int = 5
  
  val res19 = lengthFL(l2)                        //> res19  : Int = 4
  val res20 = lengthFL(l5)                        //> res20  : Int = 5
  
  val res21 = reverseFLTR(l5)                     //> res21  : books.funproginscala.ch3.List[Double] = Cons(4.0,Cons(3.0,Cons(2.0
                                                  //| ,Cons(1.0,Cons(0.0,Nil)))))
  val res22 = reverseFLTR(l2)                     //> res22  : books.funproginscala.ch3.List[String] = Cons(app,Cons(banana,Cons(
                                                  //| apple,Cons(ape,Nil))))
  
  val res23 = reverseFRTR(l5)                     //> res23  : books.funproginscala.ch3.List[Double] = Cons(4.0,Cons(3.0,Cons(2.0
                                                  //| ,Cons(1.0,Cons(0.0,Nil)))))
  //NOT WORKING CORRECTLY
  val res24 = reverseFLViaFR(l5)                  //> res24  : books.funproginscala.ch3.List[Double] = Cons(0.0,Cons(1.0,Cons(2.0
                                                  //| ,Cons(3.0,Cons(4.0,Nil)))))
  // Interesting! This does work, but the FR is not TR.
  // That's the difference from res24 = reverseFLViaFR above,
  // where the FR is TR and it doesn't work!
  val res25 = reverseFLViaFR2(l5)                 //> res25  : books.funproginscala.ch3.List[Double] = Cons(4.0,Cons(3.0,Cons(2.0
                                                  //| ,Cons(1.0,Cons(0.0,Nil)))))
  
  val res26 = concatLists(List(l2, l6))           //> res26  : books.funproginscala.ch3.List[String] = Cons(ape,Cons(apple,Cons(b
                                                  //| anana,Cons(app,Cons(zebra,Cons(zygote,Nil))))))
  
  val res27 = concatListsAlt(List(l2, l6))        //> res27  : books.funproginscala.ch3.List[String] = Cons(ape,Cons(apple,Cons(b
                                                  //| anana,Cons(app,Cons(zebra,Cons(zygote,Nil))))))
  
  val res28 = add1(List(4,3,2,1))                 //> res28  : books.funproginscala.ch3.List[Int] = Cons(5,Cons(4,Cons(3,Cons(2,N
                                                  //| il))))
  
  val res29 = doubleToString(List(1d, 2, 3))      //> res29  : books.funproginscala.ch3.List[String] = Cons(1.0,Cons(2.0,Cons(3.0
                                                  //| ,Nil)))
  val res29b = stringListToString(res29)          //> res29b  : String = 1.02.03.0
  
  val res30 = map(List(2, 4))(x => x * 2)         //> res30  : books.funproginscala.ch3.List[Int] = Cons(4,Cons(8,Nil))
  
  val res31 = mapAlt1(List(2, 4))(x => x * 2)     //> res31  : books.funproginscala.ch3.List[Int] = Cons(4,Cons(8,Nil))
  
  val res32 = mapAlt2(List(2, 4))(x => x * 2)     //> res32  : books.funproginscala.ch3.List[Int] = Cons(4,Cons(8,Nil))
  
  // PROBLEM HERE: reverses order!
  val res33 = mapViaFRTR(List(2, 4))(x => x * 2)  //> res33  : books.funproginscala.ch3.List[Int] = Cons(8,Cons(4,Nil))
  // PROBLEM HERE also: reverses order!
  val res34 = mapViaFLTR(List(2, 4))(x => x * 2)  //> res34  : books.funproginscala.ch3.List[Int] = Cons(8,Cons(4,Nil))
  
  val res35 = filter(List(1,2,3,4,5))(x => !(x % 2 == 0))
                                                  //> res35  : books.funproginscala.ch3.List[Int] = Cons(1,Cons(3,Cons(5,Nil)))
  
  val res36 = flatMap(List(1,2,3))(i => List(i,i))//> res36  : books.funproginscala.ch3.List[Int] = Cons(1,Cons(1,Cons(2,Cons(2,C
                                                  //| ons(3,Cons(3,Nil))))))
  
  val res37 = flatMapAlt1(List(1,2,3))(i => List(i,i))
                                                  //> res37  : books.funproginscala.ch3.List[Int] = Cons(1,Cons(1,Cons(2,Cons(2,C
                                                  //| ons(3,Cons(3,Nil))))))
  '''                                             //> res1: Char('\'') = '
}
/*





*/