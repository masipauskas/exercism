object BookStore {
  private val discounts: Set[Discount] = Set(
    BookDiscount(5, 0.25),
    BookDiscount(4, 0.2),
    BookDiscount(3, 0.1),
    BookDiscount(2, 0.05)
  )

  private def shoppingCart(cart: List[Int]): Cart = cart.groupBy(v => v).map { case (key, values) => (key, values.length) }

  def total(cart: List[Int]): Double = {
    def totalWithDiscountApplied(cart: Cart)(discounts: Set[Discount]) = {
      val (total, _) = (discounts + NoDiscount).foldLeft((0.0, cart)) { case ((balance, c), discount) =>
        discount.apply(c)(balance)
      }

      total
    }

    val aggregatedCart = shoppingCart(cart)
    discounts.subsets.map(totalWithDiscountApplied(aggregatedCart)(_)).min
  }

  type Cart = Map[Int, Int] // Cart is a map from ID -> Quantity

  trait Discount {
    val price = 8
    def apply(cart: Cart)(balance: Double): (Double, Cart)
  }

  object NoDiscount extends Discount {
    override def apply(cart: Cart)(balance: Double): (Double, Cart) = (cart.values.sum * price + balance, Map())
  }

  case class BookDiscount(numberOfBooks: Int, discount: Double, quantity: Int = 1) extends Discount {
    private def isApplicable(cart: Cart) = cart.size >= numberOfBooks && cart.forall(_._2 >= quantity)
    private def discountedCart(cart: Cart) = cart.keys.take(numberOfBooks).map(key => (key, quantity)).toMap.withDefaultValue(0)
    private def removeDiscountedFromCart(cart: Cart, discounted: Cart) = cart
      .map { case (book, qty) => (book, qty - discounted(book)) }
      .filter { case (_, qty) => qty > 0 }

    private def priceCart(cart: Cart) = cart.values.sum * price * (1 - discount)
    override def apply(cart: Cart)(balance: Double): (Double, Cart) = {
      if (!isApplicable(cart)) (balance, cart)
      else {
        val discountCart = discountedCart(cart)
        val cartWithoutDiscountedBooks = removeDiscountedFromCart(cart, discountCart)
        apply(cartWithoutDiscountedBooks)(balance + priceCart(discountCart))
      }
    }
  }
}
