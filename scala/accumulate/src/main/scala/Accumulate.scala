import scala.annotation.tailrec

class Accumulate {
  def accumulate[A, B](f: (A) => B, list : List[A]): List[B] = {
    @tailrec
    def loop(remaining: List[A], acc: List[B]): List[B] = remaining match {
      case Nil => acc
      case head :: tail => loop(tail, acc :+ f(head))
    }

    loop(list, Nil)
  }
}
