import scala.util.Try

object NthPrime {
  def prime(n: Int): Option[Int] = Try(sieve(2 to 1000000 toStream)(n - 1)).toOption

  private def sieve(numbers: Stream[Int], prior: Seq[Int] = Nil): Stream[Int] = {
    if (numbers.isEmpty) Stream.empty
    else {
      val number = numbers.head
      if (prior.forall(number % _ != 0)) number #:: sieve(numbers.tail, prior :+ number)
      else sieve(numbers.tail, prior)
    }
  }
}
