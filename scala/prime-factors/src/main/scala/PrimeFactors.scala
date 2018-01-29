import scala.annotation.tailrec

object PrimeFactors {
  def factors(n: Long): Seq[Long] = {
    @tailrec
    def loop(n: Long, f: Long, primes: Seq[Long]): Seq[Long] = {
      if (n == 1) primes
      else if (n % f == 0 && isPrime(f, primes)) loop(n / f, f, primes :+ f)
      else loop(n, f + 1, primes)
    }

    loop(n, 2, Nil)
  }

  def isPrime(candidate: Long, primes: Seq[Long]): Boolean = primes.filter(_ != candidate).forall(p => p % candidate != 0)
}
