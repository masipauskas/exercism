object SumOfMultiples {
  def sum(factors: Set[Int], limit: Int): Int = {
    def isMultiple(number: Int) = factors.exists(factor => number % factor == 0)

    (1 until limit).foldLeft(0)((acc, num) => if (isMultiple(num)) acc + num else acc)
  }
}

