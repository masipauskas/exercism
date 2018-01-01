object Sieve {
  def primes(n: Int): List[Int] = {
    def isDivisor(n: Int, prime: Int) = n % prime == 0
    def isPrime(candidate: Int, primes: List[Int]) = !primes.exists(prime => isDivisor(candidate, prime))
    (2 to n).foldLeft(List[Int]()) { case (primes, candidate) =>
      if (isPrime(candidate, primes)) primes :+ candidate
      else primes
    }
  }
}
