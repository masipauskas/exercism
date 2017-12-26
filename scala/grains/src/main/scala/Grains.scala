object Grains {
  def square(square: Int): Option[BigInt] = {
    import math.pow
    square match {
      case n if n >= 1 && n <= 32 => Some(BigDecimal(pow(2, square - 1)).toBigInt())
      case n if n > 32 && n <= 64 => Some((BigDecimal(pow(2, 31)) * BigDecimal(pow(2, square - 32))).toBigInt())
      case _ => None
    }
  }

  def total = {
    val squares = for (n <- 1 to  64) yield square(n)
    squares.flatten.sum
  }
}
