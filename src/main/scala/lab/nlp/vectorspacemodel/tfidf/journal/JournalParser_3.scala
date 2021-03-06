package lab.nlp.vectorspacemodel.tfidf.journal

object JournalParser_3 {
    
    case class JournalEntry(date: String, text: List[String])

    val datePattern = """[0-9]{1,2}/[0-9]{1,2}/[0-9]{1,4}""".r
    
    def isDate(line: String): Boolean = {
        //println("def isDate, line: " + line)
        //println("def isDate, datePattern: " + datePattern)
        datePattern.findPrefixMatchOf(line) match {
            case Some(_) => true
            case None => false
        }
    }
    
    def process(text: List[String]): List[JournalEntry] = {
        
        def inter(interText: List[String], journal: List[JournalEntry], entry: JournalEntry = null): List[JournalEntry] = {
            interText match {
                case Nil => journal :+ entry
                case line :: tail => {
                    if(isDate(line)) {
                        val date = line
                        val iterJournal = if(entry == null) journal else journal :+ entry
                        inter(tail, iterJournal, JournalEntry(date, List[String]()))
                    }
                    else {
                        val iterEntry = JournalEntry(entry.date, entry.text :+ line)
                        inter(tail, journal, iterEntry)
                    }
                }
            }
        } // End inter
        
        inter(text, List[JournalEntry]())
    } // End process
}