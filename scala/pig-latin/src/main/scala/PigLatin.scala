object PigLatin {
  private val delimiter = " "
  def translate(text: String): String = text.split(delimiter)
    .map(translateWord)
    .mkString(delimiter)


  private def translateWord(word: String): String = {
    def consonantAtTheBeginning(char: Char, idx: Int) = if (idx == 0) !vowels(char) else !(vowels(char) || char == 'y')
    val wordToProcess = word.toLowerCase()
    if (word.isEmpty) ""
    else if (vowels(wordToProcess.head) || vowelsExceptions.exists(wordToProcess.startsWith)) word + "ay"
    else {
      def makeWord(seq: Seq[Char]) = (seq ++ Seq('a', 'y')).mkString("")

      val (consonants, _) = wordToProcess.zipWithIndex.takeWhile(pair => consonantAtTheBeginning(pair._1, pair._2)).unzip
      val (rest, _) = wordToProcess.zipWithIndex.dropWhile(pair => consonantAtTheBeginning(pair._1, pair._2)).unzip
      if (consonants.endsWith("q") && rest.startsWith("u")) makeWord(rest.tail ++ consonants :+ rest.head)
      else makeWord(rest ++ consonants)
    }
  }

  private val vowels = Set('a', 'e', 'i', 'o', 'u')
  private val vowelsExceptions = Set("yt", "xr")
}
