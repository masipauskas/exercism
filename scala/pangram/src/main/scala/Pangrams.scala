object Pangrams {
  private val alphabet = "abcdefghijklmnoprstuqvwzx".toSet
  def isPangram(input: String): Boolean = alphabet.diff(input.toLowerCase.toSet).isEmpty
}

