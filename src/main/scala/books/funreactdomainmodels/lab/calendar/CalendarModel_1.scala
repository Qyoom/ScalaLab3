package books.funreactdomainmodels.lab.calendar

import java.util.Date
import scala.util.{Try, Success, Failure}

trait CalandarService {
    import CalendarEntities._
    import CalendarEntities.Days._
    
    def firstDayOfWeek: DayName
    def getWeek(date: Date): Try[Week]
    def getMonth(date: Date): Try[Month]
    def getYear(date: Date): Try[Year]
    def getHolidays(holidays: List[Holiday])
}

trait EventService {
    import CalendarEntities._
    
    def newEvent: Try[Event]
    
    def deleteEvent(event: Event): Try[Event]
    def findEvent(calendar: Calendar, name: String): Try[List[Event]]
    def editEvent(event: Event): Try[Event]
    def eventsBy(startTime: Date, endTime: Date): Try[List[Event]]
    
    def addReminder(event: Event, reminder: Reminder): Try[Reminder]
    def deleteReminder(event: Event, reminder: Reminder): Try[Reminder]
    
    def changeReminder(
        event: Event, oldReminder: Reminder, newDate: Date
    ): Try[Event] = {
        val newReminders = Reminder(newDate) :: event.reminders.filter { 
            reminder => reminder != oldReminder 
        }
        Success(event.copy(reminders = newReminders))
    }
}
    
trait InviteService {
    import CalendarEntities._
    
    // Side effects
    def invite(event: Event, email: List[Email])
    def send(invite: List[Email])
}

object CalendarEntities {
    
    object Days extends Enumeration {
        type DayName = Value
        val SUN, MON, TUE, WED, THU, FRI, SAT = Value
    }
        
    case class User(uId: String)
    
    case class Event(
        calendar: Calendar,
        name: String,
        startTime: Date, 
        endTime: Date, 
        location: Option[Location],
        notes: Option[List[String]],
        url: Option[URL],
        reminders: List[Reminder],
        invites: List[Email]
    )
    
    case class Calendar(user: User)
    case class Year(months: List[Month])
    case class Month(days: List[Day])
    case class Week(days: List[Day])
    case class Day(date: Date)
    
    case class Location(name: String, address: Address, map: Map)
    
    case class Map()
    
    case class Address()

    case class URL()
    
    case class Email(address: String)
    
    case class Reminder(before: Date)
    
    case class Holiday(date: Date, name: String)
}


