object Anagram {
  def anagrams(word: String, candidates: Seq[String]): Seq[String] = {
    def isAnagram(letters: String)(candidate: String): Boolean = {
      candidate.toLowerCase().sorted.equals(letters)
    }

    def isSameWord(word: String)(candidate: String) = {
      word == candidate.toLowerCase()
    }

    val caseInsensitiveWord = word.toLowerCase()
    val anagramLetters = caseInsensitiveWord.sorted

    candidates
        .filterNot(isSameWord(caseInsensitiveWord))
        .filter(isAnagram(anagramLetters))
  }
}
