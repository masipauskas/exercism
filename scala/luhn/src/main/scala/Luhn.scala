object Luhn {
  def valid(number: String): Boolean = {
    val withoutSpaces = number.filterNot(c => c == ' ')
    if (withoutSpaces.exists(!_.isDigit) || withoutSpaces.length < 2) false
    else {
      val sum = withoutSpaces.reverse.zipWithIndex.foldLeft(0) { case (sum, (char, idx)) =>
        val multiplier = if (idx % 2 == 0) 1 else 2
        val digit = multiplier * char.asDigit
        sum + (if (digit > 9) digit - 9 else digit)
      }

      sum % 10 == 0
    }
  }
}
