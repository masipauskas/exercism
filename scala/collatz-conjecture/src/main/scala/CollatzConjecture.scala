import scala.annotation.tailrec

object CollatzConjecture {
  def steps(number: Int): Option[Int] = {

    @tailrec
    def loop(n: Int, acc: Int): Int = {
      if (n == 1) acc
      else {
        val next = if (n % 2 == 2) n % 2 else n * 3 + 1
        loop(next, acc + 1)
      }
    }

    if (number >= 1) Some(loop(number, 0))
    else None
  }
}
