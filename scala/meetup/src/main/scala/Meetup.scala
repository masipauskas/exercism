import java.time.{DayOfWeek, LocalDate}

import Schedule.Schedule

case class Meetup(month: Int, year: Int) {

  def day(dayOfWeek: Int, schedule: Schedule): LocalDate = {
    val firstDayOfMonth = LocalDate.of(year, month, 1)
    val firstDayOfWeek = firstDayOfMonth.getDayOfWeek.getValue
    val dayOfWeekOffset = if (firstDayOfWeek <= dayOfWeek) dayOfWeek - firstDayOfWeek else dayOfWeek + 7 - firstDayOfWeek

    import Schedule._
    val weekOffset = schedule match {
      case First => 0
      case Second => 7
      case Third => 14
      case Fourth => 21
      case Teenth => if (dayOfWeekOffset >= 5) 7 else 14
      case Last => {
        val lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1)
        val lastDayOfWeek = lastDayOfMonth.getDayOfWeek.getValue

        val offset = if (lastDayOfWeek >= dayOfWeek) lastDayOfWeek - dayOfWeek else (7 + lastDayOfWeek) - dayOfWeek

        lastDayOfMonth.getDayOfMonth - (1 + offset + dayOfWeekOffset)
      }
    }

    firstDayOfMonth.plusDays(dayOfWeekOffset + weekOffset)
  }
}

object Schedule extends Enumeration {
  type Schedule = Value
  val Teenth, First, Second, Third, Fourth, Last = Value
}

object Meetup {
  val Mon = DayOfWeek.MONDAY.getValue
  val Tue = DayOfWeek.TUESDAY.getValue
  val Wed = DayOfWeek.WEDNESDAY.getValue
  val Thu = DayOfWeek.THURSDAY.getValue
  val Fri = DayOfWeek.FRIDAY.getValue
  val Sat = DayOfWeek.SATURDAY.getValue
  val Sun = DayOfWeek.SUNDAY.getValue
}
