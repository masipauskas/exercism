class Clock(private val hour: Int, private val minute: Int) {
  def +(other: Clock): Clock = Clock(this.hour + other.hour, this.minute + other.minute)
  def -(other: Clock): Clock = Clock(this.hour - other.hour, this.minute - other.minute)

  def canEqual(other: Any): Boolean = other.isInstanceOf[Clock]

  override def equals(other: Any): Boolean = other match {
    case that: Clock =>
      (that canEqual this) &&
        hour == that.hour &&
        minute == that.minute
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(hour, minute)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }


  override def toString = f"$hour%02d:$minute%02d"
}

object Clock {
  private val day = 24 * 60
  def apply(hour: Int, minute: Int): Clock = {
    val offset = (hour * 60 + minute) % day
    val minutes = if (offset < 0) day + offset else offset
    val hours = minutes % 60

    new Clock(hours, minutes - hours * 60)
  }

  def apply(minutes: Int): Clock = {
    Clock(0, minutes)
  }
}
