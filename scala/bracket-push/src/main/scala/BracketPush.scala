object BracketPush {
  def isPaired(expression: String): Boolean = {
    val accumulator = expression.foldLeft(BracketAccumulator()) { case (acc, c) => acc.push(c)  }
    accumulator.isBalanced
  }
}

case class BracketAccumulator(stack: List[Char] = Nil, outOfOrder: Boolean = false) {
  private val closeOpenPairs = Map(
    ')' -> '(',
    ']' -> '[',
    '}' -> '{'
  )
  private def isInValidClose(close: Char): Boolean = !stack.headOption.contains(closeOpenPairs(close))

  val isBalanced: Boolean = !outOfOrder && stack.isEmpty

  def push(bracket: Char): BracketAccumulator = bracket match {
    case ']' | '}' | ')' if isInValidClose(bracket) => this.copy(outOfOrder = true)
    case ']' | '}' | ')' => this.copy(stack.tail)
    case '[' | '{' | '(' => this.copy(bracket :: stack)
    case _ => this
  }
}
