sealed trait SimpleLinkedList[T] {
  def isEmpty: Boolean
  def value: T
  def add(item: T): SimpleLinkedList[T]
  def next: SimpleLinkedList[T]
  def reverse: SimpleLinkedList[T]
  def toSeq: Seq[T]
}

case class Empty[T]() extends SimpleLinkedList[T] {
  override val isEmpty: Boolean = true

  override def value: T = throw new IllegalStateException(s"Value of empty list")

  override def add(item: T): SimpleLinkedList[T] = Value(item, this)

  override val next: SimpleLinkedList[T] = this

  override val reverse: SimpleLinkedList[T] = this

  override val toSeq: Seq[T] = Nil
}

case class Value[T](override val value: T, override val next: SimpleLinkedList[T]) extends SimpleLinkedList[T] {
  override val isEmpty: Boolean = false

  override def add(item: T): SimpleLinkedList[T] = Value(value, next.add(item))

  override def reverse: SimpleLinkedList[T] = next.reverse.add(value)

  override def toSeq: Seq[T] = value +: next.toSeq
}

object SimpleLinkedList {
  def apply[T](params: T*): SimpleLinkedList[T] = params
    .foldLeft[SimpleLinkedList[T]](Empty[T]()) { case (acc, value) =>
      acc.add(value)
    }

  def fromSeq[T](seq: Seq[T]): SimpleLinkedList[T] = SimpleLinkedList(seq :_*)
}