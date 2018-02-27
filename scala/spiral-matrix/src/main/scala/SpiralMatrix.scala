object SpiralMatrix {
  def spiralMatrix(n: Int): List[List[Int]] = {
    def valueAt(x: Int, y: Int, w: Int = n, h: Int = n): Int = {
      if (y <= 0) x else w + valueAt(y - 1, w - x - 1, h - 1, w)
    }

    if (n == 0) Nil
    else {
      val spiral = for {
        y <- 0 until n
        x <- 0 until n
      } yield 1 + valueAt(x, y)

      spiral.toList.grouped(n).toList
    }
  }
}
