object Sublist {
  sealed trait Result
  case object Sublist extends Result
  case object Superlist extends Result
  case object Equal extends Result
  case object Unequal extends Result

  def sublist[T](left: List[T], right: List[T]): Result = {
    if (left == right) Equal
    else if (left.lengthCompare(right.length) < 0 && isSublist(left, right)) Sublist
    else if (left.lengthCompare(right.length) > 0 && isSublist(right, left)) Superlist
    else Unequal
  }

  private def isSublist[T](left: List[T],right: List[T]): Boolean = {
    if (right.lengthCompare(left.length) < 0) false
    else if (left == right.take(left.length)) true
    else isSublist(left, right.tail)
  }
}
