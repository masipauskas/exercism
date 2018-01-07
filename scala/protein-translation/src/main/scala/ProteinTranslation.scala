object ProteinTranslation {
  private val rnaStopCodon = Set("UAA", "UAG", "UGA")
  private val rnaCodonToProtein = Seq(
      Seq("AUG") -> "Methionine",
      Seq("UUU", "UUC") ->	"Phenylalanine",
      Seq("UUA", "UUG") -> "Leucine",
      Seq("UCU", "UCC", "UCA", "UCG") ->	"Serine",
      Seq("UAU", "UAC") -> "Tyrosine",
      Seq("UGU", "UGC") ->	"Cysteine",
      Seq("UGG") ->	"Tryptophan"
  )
    .flatMap { case (codons, protein) => codons.map(codon => codon -> protein) }
    .toMap

  def translate(protein: String): Seq[String] = {
    protein
      .grouped(3)
      .takeWhile(codon => !rnaStopCodon(codon))
      .flatMap(rnaCodonToProtein.get)
      .toSeq
  }
}
