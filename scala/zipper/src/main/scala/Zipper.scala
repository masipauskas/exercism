sealed trait Path[A] {
  val isDirty: Boolean
  val focus: BinTree[A]

  def update(transform: BinTree[A] => BinTree[A]): Path[A]
}

case class Left[A](focus: BinTree[A], isDirty: Boolean = false) extends Path[A] {
  override def update(transform: BinTree[A] => BinTree[A]): Path[A] = this.copy(transform(focus), isDirty = true)
}

case class Right[A](focus: BinTree[A], isDirty: Boolean = false) extends Path[A] {
  override def update(transform: BinTree[A] => BinTree[A]): Path[A] = this.copy(transform(focus), isDirty = true)
}

case class Zipper[A](path: List[Path[A]]) {
  def value: Path[A] = path.head
}

object Zipper {
  // Get a zipper focussed on the root node.
  def fromTree[A](bt: BinTree[A]): Zipper[A] = Zipper(Left(bt) :: Nil)

  // Get the complete tree from a zipper.
  def toTree[A](zipper: Zipper[A]): BinTree[A] = zipper.path match {
    case root :: Nil => root.focus
    case _ => toTree(up(zipper).get)
  }

  // Get the value of the focus node.
  def value[A](zipper: Zipper[A]): A = zipper.path.head.focus.value

  // Get the left child of the focus node, if any.
  def left[A](zipper: Zipper[A]): Option[Zipper[A]] = for {
    head <- zipper.value.focus.left
  } yield Zipper(Left(head) :: zipper.path)

  // Get the right child of the focus node, if any.
  def right[A](zipper: Zipper[A]): Option[Zipper[A]] = for {
    head <- zipper.value.focus.right
  } yield Zipper(Right(head) :: zipper.path)

  // Get the parent of the focus node, if any.
  def up[A](zipper: Zipper[A]): Option[Zipper[A]] = {
    if (zipper.path.lengthCompare(2) < 0) None
    else {
      val focus :: newFocus :: rest = zipper.path
      if (focus.isDirty) {
        val updatedFocus = focus match {
          case Left(tree, _) => newFocus.update(t => t.copy(left = Some(tree)))
          case Right(tree, _) => newFocus.update(t => t.copy(right = Some(tree)))
        }

        Some(Zipper(updatedFocus :: rest))
      } else Some(Zipper(newFocus :: rest))
    }
  }

  // Set the value of the focus node.
  def setValue[A](v: A, zipper: Zipper[A]): Zipper[A] = {
    val head :: rest = zipper.path
    Zipper(head.update(t => t.copy(value = v)) :: rest)
  }

  // Replace a left child tree.
  def setLeft[A](l: Option[BinTree[A]], zipper: Zipper[A]): Zipper[A] = {
    val head :: rest = zipper.path
    Zipper(head.update(t => t.copy(left = l)) :: rest)
  }

  // Replace a right child tree.
  def setRight[A](r: Option[BinTree[A]], zipper: Zipper[A]): Zipper[A] = {
    val head :: rest = zipper.path
    Zipper(head.update(t => t.copy(right = r)) :: rest)
  }
}

// A binary tree.
case class BinTree[A](value: A, left: Option[BinTree[A]], right: Option[BinTree[A]])

