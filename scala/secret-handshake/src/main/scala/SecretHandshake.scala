import scala.annotation.tailrec

object SecretHandshake {
  def commands(command: Int): List[String] = {

    @tailrec
    def inner(rest: Int, acc: List[String], shouldReverse: Boolean): List[String] = {
      if (rest >= 16) inner(rest - 16, acc, !shouldReverse)
      else if (rest >= 8) inner(rest - 8, "jump" +: acc, shouldReverse)
      else if (rest >= 4) inner(rest - 4, "close your eyes" +: acc, shouldReverse)
      else if (rest >= 2) inner(rest - 2, "double blink" +: acc, shouldReverse)
      else if (rest >= 1) inner(rest - 1, "wink" +: acc, shouldReverse)
      else if (shouldReverse) acc.reverse else acc
    }

    inner(command, Nil, shouldReverse = false)
  }
}
