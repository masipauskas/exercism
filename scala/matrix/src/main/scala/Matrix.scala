case class Matrix(data: Seq[Seq[Int]]) {
  def rows(row: Int) = data(row)
  def cols(col: Int) = data.map(row => row(col))
}

object Matrix {
  def apply(matrix: String): Matrix = {
    def parseRow(row: String): Seq[Int] = row.split(" ").map(_.toInt)
    val data = matrix.lines.map(parseRow).toSeq
    Matrix(data)
  }
}
