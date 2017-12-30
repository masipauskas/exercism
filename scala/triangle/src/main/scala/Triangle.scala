case class Triangle(left: Double, right: Double, base: Double) {
  private def isValidSide(side: Double) = side > 0

  private val isValidTriangle = (left + right > base) && (left + base > right) && (right + base > left)

  val isValid: Boolean = isValidSide(left) && isValidSide(right) && isValidSide(base) && isValidTriangle

  val equilateral: Boolean = isValid && left == right && right == base

  val isosceles: Boolean = isValid && (left == right || right == base || left == base)

  val scalene: Boolean = isValid && !isosceles
}
