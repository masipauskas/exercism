object CustomSet {
  def unapply(value: Any): Option[List[Int]] = value match {
    case c: CustomSet => Some(c.contents)
    case _ => None
  }

  def fromList(list: List[Int]): CustomSet = new CustomSet(list.distinct.sorted)

  def isEqual(left: CustomSet, right: CustomSet) = left == right

  def empty(set: CustomSet): Boolean = members(set).isEmpty

  def member(set: CustomSet, value: Int): Boolean = members(set).contains(value)

  def isSubsetOf(set: CustomSet, subsetOf: CustomSet): Boolean = members(set).forall(member(subsetOf, _))

  def isDisjointFrom(left: CustomSet, right: CustomSet): Boolean = {
    if (empty(left) || empty(right)) true
    else !members(left).exists(member(right, _))
  }

  def insert(set: CustomSet, value: Int) = CustomSet.fromList(members(set) :+ value)

  def intersection(left: CustomSet, right: CustomSet): CustomSet = CustomSet.fromList(members(left).filter(member(right, _)))

  def difference(left: CustomSet, right: CustomSet): CustomSet = CustomSet.fromList(members(left).filterNot(member(right, _)))

  def union(left: CustomSet, right: CustomSet): CustomSet = CustomSet.fromList(members(left) ++ members(right))

  private def members(set: CustomSet) = set.contents
}

class CustomSet(elements: List[Int]) {
  val contents: List[Int] = elements.distinct

  override def equals(obj: scala.Any): Boolean = obj match {
    case CustomSet(otherElements) => elements == otherElements
    case _ => false
  }
}