trait Node[T] {
  val left: Option[Node[T]]
  val right: Option[Node[T]]
  val value: T
  def insert(value: T): Node[T]
}

case class Tree[T](override val value: T, override val left: Option[Node[T]], override val right: Option[Node[T]])(implicit comparator: Ordering[T]) extends Node[T] {
  override def insert(newValue: T): Node[T] = {
    def insertInner(node: Option[Node[T]]) = node.map(_.insert(newValue)).orElse(Some(Value(newValue)))
    if (comparator.lteq(newValue, value)) this.copy(left = insertInner(left))
    else this.copy(right = insertInner(right))
  }
}

case class Value[T](override val value: T)(implicit comparator: Ordering[T]) extends Node[T] {
  override val left: Option[Node[T]] = None
  override val right: Option[Node[T]] = None

  override def insert(newValue: T): Node[T] = {
    if (comparator.lteq(newValue, value)) Tree(value, Some(Value(newValue)), None)
    else Tree(value, None, Some(Value(newValue)))
  }
}

object Bst {
  def apply[T](value: T)(implicit comparator: Ordering[T]): Node[T] = Value(value)

  def fromList[T](list: Seq[T])(implicit comparator: Ordering[T]): Node[T] = {
    val sorted = list //.sorted
    sorted.tail.foldLeft[Node[T]](apply(sorted.head))((tree, item) => tree.insert(item))
  }

  def toList[T](tree: Node[T]): Seq[T] = tree match {
    case Value(value) => Seq(value)
    case Tree(value, left, right) => left.toSeq.flatMap(toList) ++ Seq(value) ++ right.toSeq.flatMap(toList)
  }
}