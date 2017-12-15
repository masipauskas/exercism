object DifferenceOfSquares {

  def sumOfSquares(n: Int): Int = series(n).map(square).sum

  def squareOfSum(n: Int): Int = square(series(n).sum)

  def differenceOfSquares(n: Int): Int = squareOfSum(n) - sumOfSquares(n)

  private def series(n: Int) = 1 to n

  private def square(n: Int) = n * n
}
