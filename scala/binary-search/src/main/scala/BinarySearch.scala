object BinarySearch {
  def find(items: List[Int], item: Int, currentMiddle: Int = 0): Option[Int] = {
    lazy val middle: Int = items.length / 2
    lazy val currentItem = items(middle)

    items match {
      case Nil => None
      case last :: Nil if last != item => None
      case _: List[Int] if currentItem == item => Some(currentMiddle + middle)
      case _: List[Int] if currentItem < item =>
        find(items.drop(middle), item, currentMiddle + middle)
      case _: List[Int] if currentItem > item =>
        find(items.take(middle), item, currentMiddle)
    }
  }
}
