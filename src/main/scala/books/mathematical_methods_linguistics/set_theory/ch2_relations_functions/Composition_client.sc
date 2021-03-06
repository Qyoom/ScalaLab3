package books.mathematical_methods_linguistics.set_theory.ch2_relations_functions

import Identity._
import Function._
import CartesianProduct._
import books.mathematical_methods_linguistics.set_theory.ch1_basic_concepts.SetOperations1._


object Composition_client {

  // figure 2-6

  val A = Set('a', 'b')                           //> A  : scala.collection.immutable.Set[Char] = Set(a, b)
  val B = Set('c', 'd', 'e')                      //> B  : scala.collection.immutable.Set[Char] = Set(c, d, e)
  val C = Set('f', 'g', 'h')                      //> C  : scala.collection.immutable.Set[Char] = Set(f, g, h)
  
  // Relations
  val R = Set(('a','d'), ('a','e'), ('b','c'))    //> R  : scala.collection.immutable.Set[(Char, Char)] = Set((a,d), (a,e), (b,c))
                                                  //| 
  val S = Set(('c','f'), ('e','g'), ('e','h'))    //> S  : scala.collection.immutable.Set[(Char, Char)] = Set((c,f), (e,g), (e,h))
                                                  //| 
  // ---------------- //
  
  // Cartesians (not functions)
  cartProd(A, B)                                  //> res0: Set[(Char, Char)] = Set((a,e), (a,c), (b,e), (a,d), (b,d), (b,c))
  assert(isSubset( cartProd(A, B), R) )
  
  cartProd(B, C)                                  //> res1: Set[(Char, Char)] = Set((e,g), (d,f), (c,f), (c,g), (d,g), (d,h), (c,h
                                                  //| ), (e,h), (e,f))
  assert(isSubset( cartProd(B, C), S) )
  
  cartProd(A, C)                                  //> res2: Set[(Char, Char)] = Set((a,f), (b,f), (b,g), (a,g), (b,h), (a,h))
  
  val AtoB = relation(A)(B)(R)                    //> AtoB  : Set[Char] = Set(d, e, c)
  assert(AtoB == B)
  val BtoC = relation(AtoB)(C)(S)                 //> BtoC  : Set[Char] = Set(g, h, f)
  assert(BtoC == C)
  
  val AtoC = relation(relation(A)(B)(R))(C)(S)    //> AtoC  : Set[Char] = Set(g, h, f)
  assert(AtoC == C)
  
  // -------------------- //
  
  composeRel(R, S)                                //> res3: Set[(Char, Char)] = Set((a,g), (a,h), (b,f))
    
  assert(value(composeRel(R, S)) == C)
  
  assert(isSubset(cartProd(A, C), composeRel(R, S)))
  
  '''                                             //> res4: Char('\'') = '
}