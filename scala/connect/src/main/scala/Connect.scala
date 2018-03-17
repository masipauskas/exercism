import Connect.Color.TPlayer
import Connect.{Color, Coordinate}

class Connect(grid: Seq[Seq[String]]) {
  def winner: Option[TPlayer] = whiteWon() orElse blackWon()

  private def whiteWon() = {
    val reachedWhiteGoal = (c: Coordinate) => c.y == grid.length - 1
    val whiteStartingCoordinates = grid.head.indices.map(x => Coordinate(x, 0))
    checkWin(whiteStartingCoordinates, Color.White, reachedWhiteGoal)
  }

  private def blackWon() = {
    val reachedBlackGoal = (c: Coordinate) => c.x == grid.head.length - 1
    val blackStartingCoordinates = grid.indices.map(y => Coordinate(0, y))
    checkWin(blackStartingCoordinates, Color.Black, reachedBlackGoal)
  }

  private def checkWin(from: Seq[Coordinate], player: TPlayer, winningCondition: Coordinate => Boolean): Option[TPlayer] = {
    val won = from
      .filter(isOfType(_, player))
      .exists { start =>
        val visited = Array.fill(grid.length, grid.head.length)(false)
        hasPath(start, player, winningCondition, visited)
      }
    if (won) Some(player) else None
  }

  private def hasPath(from: Coordinate, player: TPlayer, reached: Coordinate => Boolean, visited: Array[Array[Boolean]]): Boolean = {
    if (reached(from)) true
    else if (visited(from.y)(from.x)) false
    else {
      visited(from.y).update(from.x, true)

      val others = next(from, player, visited)
      if (others.isEmpty) false
      else others.exists(f => hasPath(f, player, reached, visited))
    }
  }

  private def next(from: Coordinate, player: TPlayer, visited: Array[Array[Boolean]]): Seq[Coordinate] = {
    def alreadyVisited(coordinate: Coordinate) = visited(coordinate.y)(coordinate.x)

    offsets.map(from.add)
      .filter(isOfType(_, player))
      .filterNot(alreadyVisited)
  }

  private def isOfType(coordinate: Coordinate, player: TPlayer): Boolean = {
    if (coordinate.x < 0 || coordinate.x >= grid.head.length || coordinate.y < 0 || coordinate.y >= grid.length) false
    else grid(coordinate.y)(coordinate.x) == player.toString
  }

  private val offsets = Seq(
    Coordinate(0, -1), Coordinate(0, 1),
    Coordinate(-1, 0), Coordinate(1, 0),
    Coordinate(1, -1), Coordinate(-1, 1)
  )
}

object Connect {
  def apply(grid: Seq[String]): Connect = new Connect(grid.map(_.trim.split("").toSeq))

  object Color extends Enumeration {
    type TPlayer = Value
    val Black: Color.Value = Value("X")
    val White: Color.Value = Value("O")
    val Empty: Color.Value = Value(".")
  }

  case class Coordinate(x: Int, y: Int) {
    def add(coordinate: Coordinate): Coordinate = this.copy(x + coordinate.x, y + coordinate.y)
  }
}
