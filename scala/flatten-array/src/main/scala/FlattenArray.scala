import scala.annotation.tailrec

object FlattenArray {
  def flatten(list: List[Any]): List[Any] = {
    @tailrec
    def inner(list: List[Any], acc: List[Any]): List[Any] = list match {
      case Nil | null => acc
      case (Nil | null) :: rest => inner(rest, acc)
      case (l: List[Any]) :: rest => inner(l ++ rest, acc)
      case head :: rest => inner(rest, acc :+ head)
    }

    inner(list, Nil)
  }
}
