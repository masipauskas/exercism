class Robot {
  reset()
  private var serialNumber: String = _

  def name: String = serialNumber
  def reset(): Unit = serialNumber = Robot.generate
}

object Robot {
  private var counter = 999
  private var letters = "ZZ"
  private def generate = {
    synchronized {
      incrementSerialNumber()
      f"$letters%s$counter%03d"
    }
  }

  private def incrementSerialNumber(): Unit = {
    counter = increment(counter, 1000)
    if (counter == 0) {
      val updatedPrefix = letters.updated(1, increment(letters(1)))
      letters =  if (updatedPrefix(1) == 'A') updatedPrefix.updated(0, increment(updatedPrefix(0))) else updatedPrefix
    }
  }

  private def increment(char: Char): Char = {
    val nextCharCode = 65 + increment(char.getNumericValue - 10, 26)
    nextCharCode.toChar
  }

  private def increment(number: Int, max: Int): Int = (number + 1) % max
}