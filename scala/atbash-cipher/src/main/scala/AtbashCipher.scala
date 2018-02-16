object AtbashCipher {
  private val alphabet = "abcdefghijklmnopqrstuvwxyz"
  private val encoder = alphabet.zip(alphabet.reverse).toMap
  private val decoder = alphabet.reverse.zip(alphabet).toMap
  private val default: PartialFunction[Char, Char] = {
    case c: Char => c
  }

  def encode(text: String): String = {
    text
      .toLowerCase()
      .filter(c => c.isLetterOrDigit)
      .map(encoder orElse default)
      .grouped(5)
      .mkString(" ")
  }

  def decode(text: String): String = {
    text
      .filter(c => c.isLetterOrDigit)
      .map(decoder orElse default)
  }
}
