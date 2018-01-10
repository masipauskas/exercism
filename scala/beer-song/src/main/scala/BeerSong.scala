object BeerSong {
  def verse(verse: Int): String = {
    firstSentence(verse) + secondSentence(verse)
  }
  def verses(firstVerse: Int, lastVerse: Int): String = {
    val step = -1
    (firstVerse to lastVerse by step)
      .map(verse)
      .mkString("\n")
  }

  private def firstSentence(n: Int) = n match {
    case 0 => "No more bottles of beer on the wall, no more bottles of beer.\n"
    case 1 => "1 bottle of beer on the wall, 1 bottle of beer.\n"
    case n => s"$n bottles of beer on the wall, $n bottles of beer.\n"
  }

  private def secondSentence(n: Int) = n match {
    case 0 => "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    case 1 => "Take it down and pass it around, no more bottles of beer on the wall.\n"
    case 2 => "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    case _ => s"Take one down and pass it around, ${n - 1} bottles of beer on the wall.\n"
  }
}
