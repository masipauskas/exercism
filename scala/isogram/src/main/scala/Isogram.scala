object Isogram {
  def isIsogram(candidate: String): Boolean = {
    val word = candidate.toLowerCase().replaceAll("\\W", "")
    word.length == word.distinct.length
  }
}
