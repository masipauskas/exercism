object OcrNumbers {
  private val lineWidth = 3
  private val lineHeight = 4

  def convert(text: Seq[String]): String = {
    val isValid = text.length % lineHeight == 0 && text.forall(_.length % lineWidth == 0)

    if (!isValid) "?"
    else {
      val characters = text.grouped(lineHeight).map(chunk)
      characters.map { line => line.map { case Parser(v) => v }.mkString("") }.mkString(",")
    }
  }

  private def chunk(line: Seq[String]): Seq[Seq[String]] = {
    val fragments = line
      .map(_.grouped(lineWidth).toIndexedSeq)
      .toIndexedSeq

    for { i <- fragments.head.indices } yield Seq(fragments(0)(i), fragments(1)(i), fragments(2)(i), fragments(3)(i))
  }
}

sealed trait Parser {
  val pattern: Seq[String]
  val value: String
  def parse(text: Seq[String]): Option[String] = {
    if (pattern.zip(text).forall(pair => pair._1 == pair._2)) Some(value)
    else None
  }

  def unapply(arg: Seq[String]): Option[String] = this.parse(arg)
}

object Parser {
  def unapply(arg: Seq[String]): Option[String] = {
    val result = arg match {
      case ZeroParser(v) => v
      case OneParser(v) => v
      case TwoParser(v) => v
      case ThreeParser(v) => v
      case FourParser(v) => v
      case FiveParser(v) => v
      case SixParser(v) => v
      case SevenParser(v) => v
      case EightParser(v) => v
      case NineParser(v) => v
      case _ => "?"

    }

    Some(result)
  }
}

object ZeroParser extends Parser {
  override val pattern: Seq[String] = Seq(
    " _ ",
    "| |",
    "|_|",
    "   ")

  override val value: String = "0"
}

object OneParser extends Parser {
  override val pattern: Seq[String] = Seq(
    "   ",
    "  |",
    "  |",
    "   ")

  override val value: String = "1"
}

object TwoParser extends Parser {
  override val pattern: Seq[String] = Seq(
    " _ ",
    " _|",
    "|_ ",
    "   ")

  override val value: String = "2"
}

object ThreeParser extends Parser {
  override val pattern: Seq[String] = Seq(
    " _ ",
    " _|",
    " _|",
    "   ")

  override val value: String = "3"
}

object FourParser extends Parser {
  override val pattern: Seq[String] = Seq(
    "   ",
    "|_|",
    "  |",
    "   ")

  override val value: String = "4"
}

object FiveParser extends Parser {
  override val pattern: Seq[String] = Seq(
    " _ ",
    "|_ ",
    " _|",
    "   ")

  override val value: String = "5"
}

object SixParser extends Parser {
  override val pattern: Seq[String] = Seq(
    " _ ",
    "|_ ",
    "|_|",
    "   ")

  override val value: String = "6"
}

object SevenParser extends Parser {
  override val pattern: Seq[String] = Seq(
    " _ ",
    "  |",
    "  |",
    "   ")

  override val value: String = "7"
}

object EightParser extends Parser {
  override val pattern: Seq[String] = Seq(
    " _ ",
    "|_|",
    "|_|",
    "   ")

  override val value: String = "8"
}

object NineParser extends Parser {
  override val pattern: Seq[String] = Seq(
    " _ ",
    "|_|",
    " _|",
    "   ")

  override val value: String = "9"
}