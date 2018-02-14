object PythagoreanTriplet {
  type Triplet = (Int, Int, Int)

  def isPythagorean(triplet: Triplet): Boolean = {
    val a :: b :: c :: Nil = Seq(triplet._1, triplet._2, triplet._3).sorted
    a * a + b * b == c * c
  }

  def pythagoreanTriplets(from: Int, to: Int): Seq[Triplet] = {
    val results = for {
      a <- from to to
      b <- from to to if b > a
      c <- from to to if c > b

      triplet = (a, b, c) if isPythagorean((a, b, c))
    } yield triplet

    results
  }
}
