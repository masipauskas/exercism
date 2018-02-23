case class Queen(x: Int, y: Int)

object Queen {
  def create(x: Int, y: Int): Option[Queen] = {
    def isInBounds(x: Int) = x >= 0 && x <= 7
    if (isInBounds(x) && isInBounds(y)) Some(Queen(x, y))
    else None
  }
}
