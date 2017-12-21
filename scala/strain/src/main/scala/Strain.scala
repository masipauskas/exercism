object Strain {
  def keep[A](list: Iterable[A], predicate: A => Boolean): Iterable[A] = for (item <- list if predicate(item)) yield item
  def discard[A](list: Iterable[A], predicate: A => Boolean): Iterable[A] = keep[A](list, v => !predicate(v))
}