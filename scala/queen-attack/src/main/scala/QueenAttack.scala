object QueenAttack {
  def canAttack(left: Queen, right: Queen): Boolean = {
    isSameRow(left, right) || isSameColumn(left, right) || isSameDiagonal(left, right)
  }

  private def isSameRow(left: Queen, right: Queen) = left.x == right.x
  private def isSameColumn(left: Queen, right: Queen) = left.y == right.y
  private def isSameDiagonal(left: Queen, right: Queen) = {
    import math.abs
    val x = abs(left.x - right.x)
    val y = abs(left.y - right.y)

    x == y
  }
}
