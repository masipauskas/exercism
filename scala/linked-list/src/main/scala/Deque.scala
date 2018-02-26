trait Deque[T] {
  def pop(): Option[T]
  def push(value: T): Unit
  def shift(): Option[T]
  def unshift(value: T): Unit
}

object Deque {
  def apply[T](): Deque[T] = new DoublyLinkedList[T]()
}

class DoublyLinkedList[T] extends Deque[T] {
  private var head: Option[Node[T]] = None
  private var last: Option[Node[T]] = None

  override def pop(): Option[T] = head match {
    case None => None
    case Some(Node(value, None, None)) =>
      head = None
      last = None
      Some(value)
    case Some(Node(value, next, _)) =>
      head = next
      next.foreach(_.prior = None)
      Some(value)
  }

  override def push(value: T): Unit = head match {
    case None =>
      head = Some(Node(value))
      last = head
    case _ =>
      val prior = Some(Node(value, head))
      head.foreach(_.prior = prior)
      head = prior
  }

  override def shift(): Option[T] = last match {
    case None => None
    case Some(Node(value, None, prior)) =>
      last = prior
      last.foreach(_.next = None)
      Some(value)
  }

  override def unshift(value: T): Unit = last match {
    case None => push(value)
    case _ =>
      val next = Some(Node(value, prior = last))
      last.foreach(_.next = next)
      last = next
  }
}

case class Node[T](value: T, var next: Option[Node[T]] = None, var prior: Option[Node[T]] = None)