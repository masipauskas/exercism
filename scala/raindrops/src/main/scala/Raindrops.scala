import scala.annotation.tailrec

object Raindrops {
  def convert(n: Int): String = {
    val factors = factorize(n)
    val raindrops = factors.collect {
      case 3 => "Pling"
      case 5 => "Plang"
      case 7 => "Plong"
    }

    if (raindrops.nonEmpty) raindrops.mkString("") else n.toString
  }

  private def factorize(n: Int): List[Int] = {
    @tailrec
    def loop(remainder: Int, factor: Int = 2, factors: List[Int] = Nil): List[Int] = (factor * factor) > remainder match {
      case false if remainder % factor == 0 => loop(remainder / factor, factor, factor :: factors)
      case false => loop(remainder, factor + 1, factors)
      case true => remainder :: factors
    }

    loop(n).reverse.distinct
  }
}

