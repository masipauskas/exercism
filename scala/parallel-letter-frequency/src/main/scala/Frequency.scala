import scala.language.implicitConversions
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Frequency {
  def frequency(numWorkers: Int, texts: Seq[String]): Map[Char, Int] = {

    val size: Int = if (texts.lengthCompare(1) > 0) texts.length / numWorkers else 1

    val workers = for { chunk <- texts.grouped(size) } yield Future(processUnit(chunk))
    val results = Future
      .sequence(workers.toSeq)
      .map(aggregate)

    Await.result(results, 1 seconds)
  }

  def processUnit(text: Seq[String]): Map[Char, Int] = aggregate(text.map(processLine))


  def processLine(text: String): Map[Char, Int] = {
    def frequency(pair: (Char, String)) = (pair._1, pair._2.length)

    text.toLowerCase()
      .filter(_.isLetter)
      .groupBy(identity)
      .map(frequency)
  }

  def aggregate(results: Seq[Map[Char, Int]]): Map[Char, Int] = {
    def count(pairs: Seq[(Char, Int)]): Int = pairs.map(_._2).sum

    results
      .flatMap(_.toSeq)
      .groupBy(_._1)
      .mapValues(count)
  }
}
