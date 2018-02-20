object CryptoSquare {
  import Math.{sqrt, floor, ceil}

  def normalizedPlaintext(text: String): String = text.toLowerCase().filter(_.isLetterOrDigit)

  def plaintextSegments(text: String): List[String] = {
    val normalized = normalizedPlaintext(text)
    normalized.grouped(cols(normalized)).toList
  }

  def encoded(text: String): String = {
    def normalize(text: String, padding: Seq[String]): Seq[String] = text.map(_.toString) ++ padding.take(padding.length - text.length)
    val segments = plaintextSegments(text)
    val padding = for { _ <- 0 until segments.headOption.getOrElse("").length } yield ""

    segments
      .map(normalize(_, padding))
      .transpose
      .flatten
      .mkString("")
  }

  def ciphertext(text: String): String = {
    if (text.isEmpty) text
    else {
      val cipher = encoded(text)
      val c = cols(cipher)
      val r = rows(cipher)
      val padding = c * r - cipher.length

      val (full, padded) = cipher.splitAt(r * (c - padding))

      val fullColumns = full.grouped(r)
      val paddedColumns = padded.grouped(r - 1).map(_ + " ")
      (fullColumns ++ paddedColumns).mkString(" ")
    }
  }

  private def rows(cipher: String): Int = floor(square(cipher)).toInt

  private def cols(cipher: String): Int = ceil(square(cipher)).toInt

  private def square(cipher: String): Double = if (sqrt(cipher.length) != 0) sqrt(cipher.length) else 1

}
