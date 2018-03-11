object Dominoes {
  def chain(dominoes: Seq[(Int, Int)]): Option[List[(Int, Int)]] = {
    val dominoOrdering = new Ordering[(Int, Int)] {
      override def compare(x: (Int, Int), y: (Int, Int)): Int = {
        if (x._1 == y._1) {
          if (x._1 == x._2) -1
          else if (y._1 == x._2) +1
          else x._2 - y._2
        }
        else x._1 - y._1
      }

    }
    chainDominoes(dominoes.sorted(dominoOrdering))
  }

  private def chainDominoes(dominoes: Seq[(Int, Int)]): Option[List[(Int, Int)]] = {
    def isChain(chain: Seq[(Int, Int)]) = chain.head._1 == chain.last._2
    def inner(first: Int, last: Int, rest: Seq[(Int, Int)], chain: Seq[(Int, Int)]): Option[List[(Int, Int)]] = rest match {
      case Nil if isChain(chain) => Some(chain.toList)
      case Nil => None
      case _ =>
        val result = for {
          (next, rest) <- findNextDominoe(last, rest)
          result <- inner(first, next._2, rest, chain :+ next)
        } yield result
        result.headOption
    }

    if (dominoes.isEmpty) Some(List())
    else {
      val first = dominoes.head
      inner(first._1, first._2, dominoes.tail, Seq(first))
    }
  }

  private def isChainElement(n: Int)(pair: (Int, Int)) = pair._1 == n || pair._2 == n

  private def findNextDominoe(n: Int, rest: Seq[(Int, Int)]): Seq[((Int, Int), Seq[(Int, Int)])] = {
    val next = rest.filter(isChainElement(n))
    next.map { dominoe => (align(n, dominoe), remove(dominoe, rest)) }
  }

  private def remove(dominoe: (Int, Int), dominoes: Seq[(Int, Int)]) = {
    val idx = dominoes.indexOf(dominoe)
    dominoes.take(idx) ++ dominoes.drop(idx + 1)
  }

  private def align(number: Int, dominoe: (Int, Int)) = {
    if (dominoe._1 == number) dominoe else dominoe.swap
  }
}
