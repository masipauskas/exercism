object PascalsTriangle {
  def rows(n: Int): List[List[Int]] = {
    def triangle(current: Int, rows: List[List[Int]]): List[List[Int]] = {
      if (current >= n) rows.reverse
      else {
        val row = (1 :: rows.headOption.getOrElse(Nil).sliding(2).map(_.sum).toList) :+ 1
        triangle(current + 1, row :: rows)
      }
    }

    n match {
      case _ if n <= 0 => Nil
      case _ => triangle(2, List(List(1, 1), List(1))).take(n)
    }
  }
}
