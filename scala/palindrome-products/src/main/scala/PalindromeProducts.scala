class PalindromeProducts(from: Int, to: Int) {
  def smallest: Option[(Int, Set[(Int, Int)])] = palindromes(Ordering.Int)
  def largest: Option[(Int, Set[(Int, Int)])] = palindromes(Ordering.Int.reverse)

  private def palindromes(ordering: Ordering[Int]): Option[(Int, Set[(Int, Int)])] = {
    val result = for {
      x <- from to to
      y <- from to to if y <= x
      candidate = x * y if isPalindrome(candidate)
    } yield (candidate, (y, x))

    result.sortBy(_._1)(ordering).headOption.map { case (palindrome, _) =>
      val factors = result.filter(_._1 == palindrome).map(_._2).toSet
      (palindrome, factors)
    }
  }

  private def isPalindrome(int: Int): Boolean = int.toString.reverse == int.toString
}

object PalindromeProducts {
  def apply(from: Int, to: Int): PalindromeProducts = new PalindromeProducts(from, to)
}
