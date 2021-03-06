package lab.monad

object Grandfathers {
  
	def maternalGrandfather(p: Person): Maybe[Person] = {
		/* This is trait Maybe def flatMap
		 * which is instantiated in both case class Just and object MaybeNot.
		 * So if p.mother exists, Person.relationship maps Some(m) => Just(m)
		 * and the Just.flatMap implementation calls f(a). f is _.father and 
		 * a is the mother Person (i.e. the maternal grandfather). We transform
		 * Some to Just in order to provide application behavior in f.
   		 * If p has no mother, Person.relationship maps None => MaybeNot and 
	   	 * MaybeNot uses flatMap(f: Nothing => Maybe[B]) = MaybeNot
	   	 */
		p.mother flatMap { _.father } // "_.father" is an argument to the flatMap function parameter as implemented for trait Maybe by Just. But MaybeNot ignores this param and supplies its own, which is Nothing.
	} 
 
  	def maternalGrandfatherNoFlatMap(p: Person): Maybe[Person] =
		p.mother match {
		    case Just(m) => m.father
		    case MaybeNot => MaybeNot
	  	}
  
	def bothGrandfathersFlatMap(p: Person): Maybe[(Person, Person)] =
		p.mother flatMap { m =>
		    m.father flatMap { fm =>
		      	p.father flatMap { f =>
			        f.father flatMap { ff => 
			          	Just(fm, ff) // Never gets this far if any parent is MaybeNot along the way.
			        }
		      	}
		    }
	  	}
	 
	def bothGrandfathersNoFlatMap(p: Person): Maybe[(Person, Person)] =
		(p.mother, p.father) match {
		    case (Just(m), Just(f)) =>
		      	(m.father, f.father) match {
			        case (Just(fm), Just(ff)) => Just((fm, ff))
			        case _ => MaybeNot
		      	}
		    case _ => MaybeNot
	  	}

	def bothGrandfathersForComp(p: Person): Maybe[(Person, Person)] =
		/* If MaybeNot (i.e. None) occures at any point, the chain stops
	     * and that MaybeNot becomes the returned result. Note that the
	     * reason the chain stops is that MaybeNot's version of flatMap
	     * does not take the right side of the arrow as parameter, but
	     * just returns MaybeNot which ends the continuation. */
		for(
			m <- p.mother; // flatMap
			fm <- m.father; // flatMap
			f <- p.father; // flatMap
			ff <- f.father // map
		) yield (fm, ff)
	
	// ---- Assertions ------- //
	
	def assertBothGrandfathers(
		bothGrandfathers1: Person => Maybe[(Person, Person)],
		bothGrandfathers2: Person => Maybe[(Person, Person)]
	) = Person.persons foreach { p =>
	    assert(bothGrandfathers1(p) == bothGrandfathers2(p))
	}
	 
	//assertBothGrandfathers(bothGrandfathersFlatMap, bothGrandfathersNoFlatMap) 
	//assertBothGrandfathers(bothGrandfathersForLoop, bothGrandfathersFlatMap)

}