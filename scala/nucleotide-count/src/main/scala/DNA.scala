class DNA(_sequence: String) {
  private val nucleotideStrands = Set('A', 'C', 'G', 'T')

  lazy val nucleotideCounts: Either[InvalidNucleotideSequence, Map[Char, Int]] = validate(_sequence).map(count)

  private def validate(sequence: String): Either[InvalidNucleotideSequence, Array[Char]] = {
    if (sequence.forall(nucleotideStrands)) Right(sequence.toCharArray)
    else Left(InvalidNucleotideSequence(sequence))
  }

  private def count(sequence: Array[Char]): Map[Char, Int] = {
    val template = nucleotideStrands.map(strand => (strand, 0)).toMap
    template ++ sequence
      .groupBy(identity)
      .mapValues(_.length)
  }

  case class InvalidNucleotideSequence(n: String)
}
