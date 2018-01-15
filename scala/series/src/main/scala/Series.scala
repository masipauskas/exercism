import scala.collection.immutable

object Series {
  def slices(n: Int, text: String): List[immutable.IndexedSeq[Int]] = {
    text
      .map(_.asDigit)
      .sliding(n, 1)
      .toList
  }
}
