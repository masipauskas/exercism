class WordCount(text: String) {
  val countwords: Map[String, Int] = text
    .toLowerCase()
    .split("""(^\')|(\W\')|(\'\W)|[^(\w+\'?\w*)]""")
    .filter(_.nonEmpty)
    .groupBy(identity)
    .mapValues(_.length)
}

object WordCount {
  def apply(text: String): WordCount = new WordCount(text)
}
