import Allergen.TAllergen

object Allergies {
  def allergicTo(allergen: TAllergen, score: Int): Boolean = {
    (allergen.id & score) == allergen.id
  }

  def list(score: Int): List[TAllergen] = {
    Allergen.values
      .filter(allergen => allergicTo(allergen, score))
      .toList
  }
}

object Allergen extends Enumeration {
  type TAllergen = Value
  val Eggs: Allergen.Value = Value(1)
  val Peanuts: Allergen.Value = Value(2)
  val Shellfish: Allergen.Value = Value(4)
  val Strawberries: Allergen.Value = Value(8)
  val Tomatoes: Allergen.Value = Value(16)
  val Chocolate: Allergen.Value = Value(32)
  val Pollen: Allergen.Value = Value(64)
  val Cats: Allergen.Value = Value(128)
}
