object PerfectNumbers {
  object NumberType extends Enumeration {
    type NumberType = Value
    val Perfect, Abundant, Deficient = Value
  }

  def classify(number: Int): Either[String, NumberType.NumberType] = {
    def classify(number: Int, sumOfFactors: Int) = {
      if (sumOfFactors == number) NumberType.Perfect
      else if (sumOfFactors > number) NumberType.Abundant
      else NumberType.Deficient
    }
    if (number < 1) Left("Classification is only possible for natural numbers.")
    else {
      val sumOfFactors = factorize(number).sum - number
      val numberType = classify(number, sumOfFactors)
      Right(numberType)
    }
  }

  private def factorize(number: Int): Set[Int] = {
    def divisor(n: Int) = {
      if (number % n == 0) {
        if (n != number / n) Seq(n, number / n)
        else Seq(n)
      }
      else Seq()
    }

    import scala.math.sqrt
    (1 to sqrt(number).ceil.toInt)
      .flatMap(d => divisor(d)).toSet
  }
}
