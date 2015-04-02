package typesystem.parameterization

object Parameterization_lab_1 {

	'a'.toInt                                 //> res0: Int = 97
	
	class Stack[T] {
		var elems: List[T] = Nil
		def push(x: T) { elems = x :: elems }
		def peek: T = elems.head
		def pop() { elems = elems.tail }
		def top(): T = {
			val x = elems.head
			pop
			x
		}
		override def toString = s"$elems"
	}
	
	val stack = new Stack[Int]                //> stack  : typesystem.parameterization.Parameterization_lab_1.Stack[Int] = Lis
                                                  //| t()
	stack push(1)
	stack push('a') // implicit conversion
	stack push(3)
	stack push(4)
	stack.peek                                //> res1: Int = 4
	stack                                     //> res2: typesystem.parameterization.Parameterization_lab_1.Stack[Int] = List(4
                                                  //| , 3, 97, 1)
	stack.pop
	stack                                     //> res3: typesystem.parameterization.Parameterization_lab_1.Stack[Int] = List(3
                                                  //| , 97, 1)
	stack.top                                 //> res4: Int = 3
	stack.peek                                //> res5: Int = 97
}
/*



*/