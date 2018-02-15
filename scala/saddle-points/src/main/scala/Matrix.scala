class Matrix(points: Array[Array[Int]]) {
  private def isSaddlePoint(row: Int, col: Int) = {
    val value = points(row)(col)
    val isGreaterEqualInARow = points(row).forall(_ <= value)
    val isLessEqualInACol = points.forall(row => row(col) >= value)

    isGreaterEqualInARow && isLessEqualInACol
  }

  val saddlePoints: Set[(Int, Int)] = {
    val rows = points.length
    val cols = points.headOption.map(_.length).getOrElse(0)

    val result = for {
      row <- 0 until rows
      col <- 0 until cols

      pair = (row, col) if isSaddlePoint(row, col)
    } yield pair

    result.toSet
  }
}

object Matrix {

  def apply(points: Seq[Seq[Int]]): Matrix = new Matrix(points.map(_.toArray).toArray)
}