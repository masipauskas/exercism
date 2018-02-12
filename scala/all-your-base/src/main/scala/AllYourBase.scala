object AllYourBase {
  def rebase(base: Int, digits: List[Int], newBase: Int): Option[List[Int]] = {
    if (!isValid(base, digits, newBase)) None
    else {
      val number = toDecimal(base, digits)
      fromDecimal(newBase, number) match {
        case Nil => Some(List(0))
        case rest => Some(rest)
      }
    }
  }

  private def isValid(base: Int, digits: List[Int], newBase: Int) = {
    def isValidBase(n: Int) = n >= 2
    def isValidDigits(base: Int, digits: List[Int]) = digits.forall(d => d >= 0 && d < base)

    isValidBase(base) && isValidBase(newBase) && isValidDigits(base, digits)
  }

  private def toDecimal(base: Int, digits: List[Int]): Int = {
    import math.pow
    digits.reverse.zipWithIndex.map { case (d, i) => d * pow(base, i).toInt }.sum
  }

  private def fromDecimal(base: Int, number: Int): List[Int] = {
    def factorise(base: Int, number: Int, acc: List[Int]): List[Int] = number match {
      case 0 => acc.reverse
      case _ => factorise(base, number / base, acc :+ number % base)
    }

    factorise(base, number, Nil)
  }
}
