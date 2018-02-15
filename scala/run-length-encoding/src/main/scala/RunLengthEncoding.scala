object RunLengthEncoding {
  def encode(text: String): String = {
    val (r, l, c) = text.foldLeft(("", "", 0)) {
      case (("", "", 0), next) => ("", next.toString, 1)
      case ((encoded, last, 1), next) if last != next.toString => (encoded + last, next.toString, 1)
      case ((encoded, last, count), next) if last == next.toString => (encoded, last, count + 1)
      case ((encoded, last, count), next) if last != next.toString => (encoded + count.toString + last, next.toString, 1)
    }

    r + (if (c <= 1) l else c.toString + l)
  }

  def decode(text: String): String = {
    val (result, _) = text.foldLeft(("", 0)) {
      case ((decoded, 0), current) if current.isDigit =>
        (decoded, current.asDigit)
      case ((decoded, count), current) if current.isDigit =>
        (decoded, (count.toString :+ current).toInt)
      case ((decoded, count), current) =>
        val c = if (count == 0) 1 else count
        val substr = for { _ <- 0 until c } yield current
        (decoded + substr.mkString(""), 0)
    }

    result
  }
}