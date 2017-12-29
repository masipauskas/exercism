import scala.collection.mutable

object RnaTranscription {
  def toRna(dnaStrand: String): Option[String] = {
    def toRnaCompliment(dna: Char): Option[Char] = dna match {
      case 'G' => Some('C')
      case 'C' => Some('G')
      case 'T' => Some('A')
      case 'A' => Some('U')
      case _ => None
    }

    val result = dnaStrand.map(toRnaCompliment)

    // we are effectively doing a traverse function using StringBuilder for concatenation
    result.foldLeft(Option(new mutable.StringBuilder)) { case (builder, char) => char match {
        case Some(strand) => builder.map(_.append(strand))
        case None => None
      }
    }.map(_.toString())
  }
}
