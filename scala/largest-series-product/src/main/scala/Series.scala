import scala.util.{Success, Try}

object Series {
  def largestProduct(n: Int, digits: String): Option[Int] = {
    implicit val intIdentity: Int = 1
    implicit val listIdentity: Iterator[Int] = Iterator.empty

    val isValidDigits = digits.forall(_.isDigit) && (digits.length >= n || n == 0)

    val result = for {
      numbers <- Try(digits.map(_.asDigit)) if isValidDigits && n >= 0
      products <- safe(numbers.sliding(n, 1).map(_.product))
      maxProduct <- safe(products.max)
    } yield maxProduct

    result.toOption
  }

  private def safe[T](body: => T)(implicit identity: T): Try[T] = {
    Try(body) orElse Success(identity)
  }
}
