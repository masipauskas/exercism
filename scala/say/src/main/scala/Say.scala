object Say {
  private val digits: Map[Int, String] = Seq(
    0 -> "zero",
    1 -> "one",
    2 -> "two",
    3 -> "three",
    4 -> "four",
    5 -> "five",
    6 -> "six",
    7 -> "seven",
    8 -> "eight",
    9 -> "nine",
    10 -> "ten",
    11 -> "eleven",
    12 -> "twelve",
    13 -> "thirteen",
    14 -> "fourteen",
    15 -> "fifteen",
    16 -> "sixteen",
    17 -> "seventeen",
    18 -> "eighteen",
    19 -> "nineteen",
    20 -> "twenty",
    30 -> "thirty",
    40 -> "forty",
    50 -> "fifty",
    60 -> "sixty",
    70 -> "seventy",
    80 -> "eighty",
    90 -> "ninety"
  ).toMap

  private val factors: Seq[(Long, String)] = Seq[(Long, String)](
    10L -> "%sty",
    100L -> "%s hundred",
    1000L -> "%s thousand",
    1000000L -> "%s million",
    1000000000L -> "%s billion"
  ).reverse

  def inEnglish(number: Long): Option[String] = {
    if (number < 0 || number > 999999999999L) None
    else Some(toWords(number, factors).toString().trim)
  }


  private def toWords(number: Long, factors: Seq[(Long, String)], stringBuilder: StringBuilder = new StringBuilder): StringBuilder = {
    if (number == 0 && stringBuilder.nonEmpty) stringBuilder
    else if (digits.contains(number.toInt)) stringBuilder.append(s" ${digits(number.toInt)}")
    else if (number < 100) {
      val keys = digits.keys.toSeq.sorted(Ordering.Int.reverse)
      val first = keys.find(key => number / key == 1).get
      val second = if (number > 10) s"-${digits((number % first).toInt)}" else ""

      stringBuilder.append(s" ${digits(first)}$second")
    }
    else {

      val applicable = factors.dropWhile(number / _._1 == 0)
      val (factor, format) = applicable.head
      val pre = number / factor
      val remainder = number % factor

      stringBuilder.append(format.format(toWords(pre, applicable.tail).toString()))
      toWords(remainder, applicable.tail, stringBuilder)
    }
  }
}
