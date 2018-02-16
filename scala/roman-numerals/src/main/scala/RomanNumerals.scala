object RomanNumerals {
  case class Numeral(value: Int, symbol: String)

  private val numerals = List(
    Numeral(1, "I"),
    Numeral(4, "IV"),
    Numeral(5, "V"),
    Numeral(9, "IX"),
    Numeral(10, "X"),
    Numeral(40, "XL"),
    Numeral(50, "L"),
    Numeral(90, "XC"),
    Numeral(100, "C"),
    Numeral(400, "CD"),
    Numeral(500, "D"),
    Numeral(900, "CM"),
    Numeral(1000, "M")
  ).sortBy(_.value)(Ordering.Int.reverse)

  def roman(n: Int): String = {
    def decode(remainder: Int, numerals: List[Numeral], acc: String): String = numerals match {
      case _ if remainder == 0 => acc
      case head :: tail if remainder / head.value > 0 =>
        val suffix = for { _ <- 0 until (remainder / head.value) } yield head.symbol
        decode(remainder % head.value, tail, acc + suffix.mkString(""))

      case _ :: tail => decode(remainder, tail, acc)
    }

    decode(n, numerals, "")
  }
}
