object House {
  private val lineSeparator = sys.props("line.separator")
  private val components = Seq(
    "lay in" -> "house that Jack built",
    "ate" -> "malt",
    "killed" -> "rat",
    "worried" -> "cat",
    "tossed" -> "dog",
    "milked" -> "cow with the crumpled horn",
    "kissed" -> "maiden all forlorn",
    "married" -> "man all tattered and torn",
    "woke" -> "priest all shaven and shorn",
    "kept" -> "rooster that crowed in the morn",
    "belonged to" -> "farmer sowing his corn",
    "" -> "horse and the hound and the horn"
  )

  def verse(v: Int): String = {
    val sentences = ((v - 1) to 0 by -1).zipWithIndex.map {
      case (verse, 0) => s"This is the ${components(verse)._2}"
      case (verse, _) =>
        val (verb, subject) = components(verse)
        s"that $verb the $subject"
    }

    sentences.mkString(lineSeparator) + s".$lineSeparator$lineSeparator"
  }

  def verses(first: Int, last: Int): String = {
    (first to last)
      .map(verse)
      .mkString("")
  }
}
