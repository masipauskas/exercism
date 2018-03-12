object Minesweeper {
  def annotate(game: Seq[String]): Seq[String] = {
    if (game.isEmpty || game.head.isEmpty) game
    else {
      val annotated = for {
        y <- game.indices
        x <- game.head.indices

        value = calculate(game)(x, y)
        cell = if (game(y)(x) == '*') '*' else value
      } yield cell

      annotated.mkString("")
        .grouped(game.head.length)
        .toSeq
    }
  }

  private def calculate(grid: Seq[String])(x: Int, y: Int) = {
    def outOfBounds(v: Int, length: Int) = v < 0 || v >= length
    def value(x: Int, y: Int) = if (outOfBounds(x, grid.head.length) || outOfBounds(y, grid.length)) ' ' else grid(y)(x)
    def score(x: Int, y: Int) = if (value(x, y) == '*') 1 else 0

    val result = for {
      xOffset <- -1 to 1
      yOffset <- -1 to 1
    } yield score(x + xOffset, y + yOffset)

    val nearbyMines = result.sum
    if (nearbyMines > 0) nearbyMines.toString.head else ' '
  }
}
