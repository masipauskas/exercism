import scala.util.Try

case class Bowling(frames: Seq[Frame] = Nil) {
  def roll(count: Int): Bowling = frames.lastOption match {
    case _ if count == 10 => Bowling(frames :+ Strike)
    case Some(OpenFrame(first)) =>
      val value = if (first + count == 10) Spare(first) else ClosedFrame(first, count)
      Bowling(frames.init :+ value)
    case _ => Bowling(frames :+ OpenFrame(count))
  }

  def score(): Either[Throwable, Int] = {
    def calculateScore(frames: Seq[Frame], acc: Int = 0, remaining: Int = 10): Int = frames match {
      case Nil | _ if remaining == 0 => acc
      case frame :: rest => calculateScore(rest, acc + frame.score(rest), remaining - 1)
    }

    Try {
      val length = frames(9) match {
        case Strike if frames(10) == Strike => 12
        case Spare(_) | Strike => 11
        case _ => 10
      }

      require(frames.lengthCompare(length) == 0)

      calculateScore(frames)
    }.toEither
  }
}

sealed trait Frame {
  def score(rest: Seq[Frame]): Int
}
case class OpenFrame(value: Int) extends Frame {
  override def score(rest: Seq[Frame]): Int = throw new IllegalArgumentException("Score from an open frame")
}

case class ClosedFrame(first: Int, second: Int) extends Frame {
  override def score(rest: Seq[Frame]): Int = {
    require(first + second <= 10)
    first + second
  }
}

case class Spare(first: Int) extends Frame {
  override def score(rest: Seq[Frame]): Int = {
    require(first  < 10)
    rest.head match {
      case Spare(f) => 10 + f
      case ClosedFrame(f, _) => 10 + f
      case OpenFrame(f) => 10 + f
      case Strike => 20
    }
  }
}

case object Strike extends Frame {
  override def score(rest: Seq[Frame]): Int = rest.take(2) match {
    case Spare(_) :: _ => 20
    case (f : ClosedFrame) :: r => 10 + f.score(r)
    case Strike :: Strike :: _ => 30
    case Strike :: Spare(first) :: _ => 20 + first
    case Strike :: ClosedFrame(first, _) :: _ => 20 + first
    case Strike :: OpenFrame(first) :: _  if first < 10 => 20 + first
    case _ => throw new IllegalArgumentException("Score from an incomplete game")
  }
}