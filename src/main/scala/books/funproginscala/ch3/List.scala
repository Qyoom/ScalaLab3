package books.funproginscala.ch3

// abstract class List[+A] // same, for all intensive purposes
sealed trait List[+A]
// data constructors
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

// companion object
object List {
    import collection.mutable.ListBuffer
    
    // companion class factory
    def apply[A](as: A*): List[A] = {
        if(as.isEmpty) Nil
        else Cons(as.head, apply(as.tail: _*))
    }
    
    def sum(ints: List[Int]): Int = ints match {
        case Nil => 0
        case Cons(h, t) => h + sum(t)
    }
    
    // tail recursive
    def sumAlt1(ints: List[Int]): Int = ints match {
        case Nil => 0
        case Cons(h, t) =>
            @annotation.tailrec
            def loop(acc: Int, ints: List[Int]): Int = ints match {
                case Nil => acc
                case Cons(h, t) => loop(h + acc, t)
            }
            loop(0, ints)
    }
    
    def product(ds: List[Double]): Double = ds match {
        case Nil => 1.0 // TODO: This is not correct for case when initial ds is Nil.
        case Cons(0.0, _) => 0.0
        case Cons(h, t) => h * product(t)
    }
    
    // captures initial empty list error
    def productAlt0(ds: List[Double]): Double = ds match {
        case Nil => sys.error("operation on empty list")
        case Cons(h, t) => 
            def inner(l: List[Double]): Double = l match {
                case Nil => 1.0
                case Cons(0.0, _) => 0.0
                case Cons(h, t) => h * inner(t)
            }
            inner(ds)
    }
    
    def productTR(ds: List[Double]): Double = ds match {
        case Nil => sys.error("Operation on empty list.")
        case Cons(x, xs) =>
        @annotation.tailrec
        def inner(l: List[Double], acc: Double): Double = l match {
            case Nil => acc
            case Cons(0.0, t) => 0.0
            case Cons(h, t) => inner(t, h * acc)
        }
        inner(ds, 1.0)
    }
    
    def tail[A](l: List[A]): List[A] = l match {
        case Nil => sys.error("operation on empty list")
        case Cons(_, t) => t
    }
    
    def drop[A](l: List[A], n: Int): List[A] = 
        if(n < 0) sys.error("negative arg to drop")
        else if(n == 0) l
        else l match {
            // I'm choosing to allow n to overstep list length
            case Nil => Nil
            case Cons(_, t) => drop(t, n - 1)
        }
    
    def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
        case Cons(h, t) => if(f(h)) dropWhile(t, f) else l
        case _ => l
    }
    
    // curried
    def dropWhileCur[A](l: List[A])(f: A => Boolean): List[A] = l match {
        case Cons(h, t) => if(f(h)) dropWhileCur(t)(f) else l
        case _ => l
    }
    
    // Reverses the order of the curried parameters
    def dropWhileCurAlt[A](f: A => Boolean)(l: List[A]): List[A] = l match {
        case Cons(h, t) => if(f(h)) dropWhileCurAlt(f)(t) else l
        case _ => l
    }
    
    // Return everything but the last element.
    def init[A](l: List[A]): List[A] = l match {
        case Nil => sys.error("init of empty list")
        case _ => 
            // Not tail recursive, vulnerable to stack overflow.
            def loop[A](l: List[A]): List[A] = l match { 
                case Cons(_, Nil) => Nil // recursive convergence condition
                case Cons(x, xs) => Cons(x, loop(xs))
            }
            loop(l)
    }
    
    // tail recursive
    def initAlt[A](l: List[A]): List[A] = l match {
        case Nil => sys.error("init of empty list")
        case _ =>
            val buf = new ListBuffer[A]
            @annotation.tailrec
            def loop(l: List[A]): List[A] = l match {
                case Cons(_, Nil) => List(buf.toList: _*)
                case Cons(h, t) => 
                    buf += h
                    loop(t)
            }
            loop(l)
    }
    
    // Sets different head
    def setHead[A](h: A, l: List[A]): List[A] = l match {
        case Nil => sys.error("set head on empty list")
        case Cons(_, xs) => Cons(h, xs)
    }
    
    def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
        case Nil => a2
        case Cons(x, xs) => Cons(x, append(xs, a2))
    }
    
    //-----------------------------------------------------------------//
    // Recursion over lists and generalizing to higher-order functions //
    //-----------------------------------------------------------------//
    
    /* RW: The fold family are accumulators (like sum).
     * It's important to understand that there are two parameter sets
     * and follow the pattern flow accordingly.
     */
    def foldRight[A,B](l: List[A], acc: B)(f: (A, B) => B): B = l match {
        case Nil => acc // TODO: This is not correct for case when initial l is Nil and the accumulator is 1 as in multiplication.
        case Cons(h, t) => f(h, foldRight(t, acc)(f))
    }
    
    // RW: Implementation with foldRight
    def sumAlt2(l: List[Int]) = foldRight(l, 0)(_ + _)
    
    def sumAlt3(l: List[Int]) = foldRight(l, 0)((h, acc) => h + acc)
    
    def prodAlt1(l: List[Double]) = foldRight(l, 1.0)(_ * _)
    
    // The right hand side of the fat arrow comes back as acc. h is the next value in the list.
    def prodAlt2(l: List[Double]) = foldRight(l, 1.0)((h, acc) => h * acc)
    
    def length[A](l: List[A]): Int = foldRight(l, 0)((_, acc) => acc + 1)
        
    @annotation.tailrec
    def foldRightTR[A,B](l: List[A], acc: B)(f: (A, B) => B): B = l match {
        case Nil => acc // TODO: This is not correct for case when initial as is Nil.
        case Cons(h, t) => foldRightTR(t, f(h, acc))(f)
    }
    
    def sumAlt4(l: List[Int]) = foldRightTR(l, 0)((h, acc) => h + acc)
    
    def prodAlt3(l: List[Double]) = foldRightTR(l, 1.0)((h, acc) => h * acc)
    
    @annotation.tailrec
    def foldLeftTR[A,B](l: List[A], acc: B)(f: (B, A) => B): B = l match {
        case Nil => acc // TODO: This is not correct for case when initial as is Nil.
        case Cons(h, t) => foldLeftTR(t, f(acc, h))(f)
    }
    
    def sumAlt5(l: List[Int]) = foldLeftTR(l, 0)((acc, h) => h + acc)
    
    def prodAlt4(l: List[Double]) = foldLeftTR(l, 1.0)((acc, h) => h * acc)
    
    def lengthFL[A](l: List[A]): Int = foldLeftTR(l, 0)((acc, _) => acc + 1)
    
    def reverseFLTR[S](l: List[S]): List[S] = 
        foldLeftTR(l, List[S]())((acc, h) => Cons(h, acc))
    
    def reverseFRTR[A](l: List[A]): List[A] = 
        foldRightTR(l, List[A]())((h, acc) => Cons(h, acc))
    
    def foldRightViaFL[A,B](l: List[A], acc: B)(f: (A,B) => B): B = 
        foldLeftTR(reverseFLTR(l), acc)((b,a) => f(a,b))
    
    // This is evidently theoretic (academic). Don't worry about not grokking it now.
    // https://github.com/fpinscala/fpinscala/blob/master/answerkey/datastructures/13.answer.scala
    def foldLeftViaFR[A,B](l: List[A], acc: B)(f: (B, A) => B): B = 
        foldRightTR(l, (b:B) => b)((a,g) => b => g(f(b,a)))(acc)
    
    // NOPE, NOT WORKING, NOT REVERSING because foldRightTR is TR
    def reverseFLViaFR[A](l: List[A]): List[A] = 
        foldLeftViaFR(l, List[A]())((acc, x) => Cons(x, acc))
        
    def foldLeftViaFR2[A,B](l: List[A], acc: B)(f: (B, A) => B): B = 
        foldRight(l, (b:B) => b)((a,g) => b => g(f(b,a)))(acc)
        // Note foldRight is not TR here.
        
    def reverseFLViaFR2[A](l: List[A]): List[A] = 
        foldLeftViaFR2(l, List[A]())((acc, x) => Cons(x, acc))
    
    /*
     * `append` simply replaces the `Nil` constructor of the first list with 
     * the second list, which is exactly the operation performed by `foldRight`.
     * foldRight's f just becomes Cons.
     * Note no TR here. TR (elsewhere) causes incorrect result.
     */
    def appendViaFR[A](a1: List[A], a2: List[A]): List[A] = {
        foldRight(a1, a2)(Cons(_, _))
    }
    
    // This is not correct because of TR!
    def appendViaFRTR[A](a1: List[A], a2: List[A]): List[A] =
        foldRightTR(a1, a2)(Cons(_, _))
    
    def concatLists[A](l: List[List[A]]): List[A] =
        foldRight(l, Nil:List[A])(appendViaFR)
        
    def concatListsAlt[A](l: List[List[A]]): List[A] =
        foldRight(l, Nil:List[A])(append)
        
    def add1(l: List[Int]): List[Int] =
        foldRight(l, Nil: List[Int])((h,t) => Cons(h+1, t))
        
    def doubleToString(l: List[Double]): List[String] =
        foldRight(l, Nil: List[String])((h, t) => Cons(h.toString, t))
        
    // RW: Mine
    def stringListToString(l: List[String]): String =
        foldRight(l, "")(_ + _)
            
    // 18
    /* 
        map
        
        A natural solution is using `foldRight`, but our implementation of 
        `foldRight` is not stack-safe. We can use `foldRightViaFoldLeft` 
        to avoid the stack overflow (variation 1), but more commonly, as with 
        our current implementation of `List`, `map` will just be implemented 
        using local mutation (variation 2). Again, note that the mutation 
        isn't observable outside the function, since we're only mutating a 
        buffer that we've allocated. 
    */
    def map[A,B](l: List[A])(f: A => B): List[B] =
        foldRight(l, Nil: List[B])((h, t) => Cons(f(h), t))
    
    // PROBLEM HERE: reverses order!
    def mapViaFRTR[A,B](l: List[A])(f: A => B): List[B] =
        foldRightTR(l, Nil: List[B])((h, t) => Cons(f(h), t))
        
    // PROBLEM HERE also: reverses order!    
    def mapViaFLTR[A,B](l: List[A])(f: A => B): List[B] =
        foldLeftTR(l, Nil: List[B])((t, h) => Cons(f(h), t))
        
    def mapAlt1[A,B](l: List[A])(f: A => B): List[B] = 
        foldRightViaFL(l, Nil: List[B])((h,t) => Cons(f(h), t))
        
    def mapAlt2[A,B](l: List[A])(f: A => B): List[B] = {
        val buf = new collection.mutable.ListBuffer[B]
        def inner(l: List[A]): Unit = l match {
            case Nil => ()
            case Cons(h, t) => 
                buf += f(h)
                inner(t)
        }
        inner(l)
        List(buf.toList: _*) // converting from the standard Scala list to the list we've defined here
    }
    
    // 19
    /* 
        The discussion about `map` also applies here.
    */
    def filter[A](l: List[A])(f: A => Boolean): List[A] =
        foldRight(l, Nil: List[A])((h, t) => if(f(h)) Cons(h, t) else t)
        
    def filter_1[A](l: List[A])(f: A => Boolean): List[A] = 
        foldRightViaFL(l, Nil:List[A])((h,t) => if (f(h)) Cons(h,t) else t)

    def filter_2[A](l: List[A])(f: A => Boolean): List[A] = {
        val buf = new collection.mutable.ListBuffer[A]
        def inner(l: List[A]): Unit = l match {
            case Nil => ()
            case Cons(h,t) => if (f(h)) buf += h; inner(t)
        }
        inner(l)
        List(buf.toList: _*) // converting from the standard Scala list to the list we've defined here
    }
    
    def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] =
        concatLists(map(l)(f))
        
    def flatMapAlt1[A,B](l: List[A])(f: A => List[B]): List[B] =
        concatLists(mapAlt2(l)(f))
}











