object Hamming {
  def distance(from: String, to: String): Option[Int] = {
    pair(from, to).map { strands =>
      strands.map(distance[Char]).sum
    }
  }

  def distance[T](pair: (T, T)): Int = if (pair._1 == pair._2) 0 else 1

  def pair(from: String, to: String): Option[Seq[(Char, Char)]] = {
    if (from.length == to.length) Some(from.zip(to))
    else None
  }
}
