object ScrabbleScore {
  private val letterScore = scoreIndex(Seq(
    "AEIOULNRST" -> 1,
    "DG" -> 2,
    "BCMP" -> 3,
    "FHVWY" -> 4,
    "K" -> 5,
    "JX" -> 8,
    "QZ" -> 10))

  def score(word: String): Int = {
    word.toUpperCase().map(letterScore).sum
  }

  def scoreIndex(config: Seq[(String, Int)]): Map[Char, Int] = {
    def lettersToScore(pair: (String, Int)) = {
      val (letters, score) = pair
      letters.map(c => c -> score)
    }

    config
      .flatMap(lettersToScore)
      .toMap
  }
}
