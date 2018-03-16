object Change {
  def findFewestCoins(amount: Int, coins: Seq[Int]): Option[Seq[Int]] = {
    findFewestCoins2(amount, coins.sorted(Ordering.Int.reverse)).map(_.sorted)
  }

  private def findFewestCoins2(amount: Int, coins: Seq[Int]): Option[Seq[Int]] = {
    if (amount == 0) Some(Nil)
    else if (coins.isEmpty || amount < 0) None
    else {
      val coin = coins.head
      val options = 1 to (amount / coin)

      val validResult = options
        .flatMap { count => spendCoins(amount, coin, count, coins.tail) }
        .sortBy(_.length)
        .headOption

      validResult orElse findFewestCoins2(amount, coins.tail)
    }
  }

  private def spendCoins(amount: Int, coin: Int, count: Int, rest: Seq[Int]) = {
    val spent = (1 to count).map(_ => coin)
    findFewestCoins2(amount - (count * coin), rest).map(rest => spent ++ rest)
  }
}
