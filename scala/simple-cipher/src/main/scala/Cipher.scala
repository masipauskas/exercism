class Cipher(val key: String) {
  private val offsets = key.map(_.toInt - 97)

  def encode(text: String): String = text
    .zipWithIndex
    .map { case (char, idx) => (97 + (char + offsets(idx) - 97) % 26).toChar }
    .mkString("")

  def decode(text: String): String = text
    .zipWithIndex
    .map { case (char, idx) => (97 + (char - offsets(idx) - 97) % 26).toChar }
    .mkString("")
}

object Cipher {
  def apply(key: Option[String]): Cipher = {

    val k = key.map(validate).getOrElse {
      val random = for (i <- 0 until 10) yield (97 + ((Math.random() * i) % 26)).toChar
      random.mkString("")
    }
    new Cipher(k)
  }

  private def validate(key: String): String = {
    if (key.nonEmpty && key.forall(c => c.isLetter && c.toInt >= 97)) key
    else throw new IllegalArgumentException(s"$key is not a valid key")
  }
}
