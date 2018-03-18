import java.util.function.Predicate

object ZebraPuzzle {

  sealed trait Resident
  case object Englishman extends Resident
  case object Spaniard extends Resident
  case object Ukrainian extends Resident
  case object Norwegian extends Resident
  case object Japanese extends Resident
  val residents = Seq(Englishman, Spaniard, Ukrainian, Norwegian, Japanese)

  sealed trait Color
  case object Red extends Color
  case object Green extends Color
  case object Ivory extends Color
  case object Yellow extends Color
  case object Blue extends Color
  val colors = Seq(Red, Green, Ivory, Yellow, Blue)

  sealed trait Pet
  case object Dog extends Pet
  case object Zebra extends Pet
  case object Snails extends Pet
  case object Fox extends Pet
  case object Horse extends Pet
  val pets = Seq(Dog, Zebra, Snails, Fox, Horse)

  sealed trait Drink
  case object Coffee extends Drink
  case object Tea extends Drink
  case object Milk extends Drink
  case object Juice extends Drink
  case object Water extends Drink
  val drinks = Seq(Coffee, Tea, Milk, Juice, Water)

  sealed trait Tobacco
  case object OldGold extends Tobacco
  case object Kools extends Tobacco
  case object Chesterfields extends Tobacco
  case object LuckyStrike extends Tobacco
  case object Parliaments extends Tobacco
  val tobacco = Seq(OldGold, Kools, Chesterfields, LuckyStrike, Parliaments)

  case class Solution(waterDrinker: Resident, zebraOwner: Resident)

  case class House(color: Color, resident: Resident, pet: Pet, drink: Drink, tobacco: Tobacco)
  case class Constraint(color: Option[Color] = None,
                        resident: Option[Resident] = None,
                        pet: Option[Pet] = None,
                        drink: Option[Drink] = None,
                        tobacco: Option[Tobacco] = None) {
    private def predicate[T](extractor: House => T)(attribute: T) = {
      (house: House) => extractor(house) == attribute
    }

    private val colorPredicate = predicate[Color](_.color)(_)
    private val residentPredicate = predicate[Resident](_.resident)(_)
    private val petPredicate = predicate[Pet](_.pet)(_)
    private val drinkPredicate = predicate[Drink](_.drink)(_)
    private val tobaccoPredicate = predicate[Tobacco](_.tobacco)(_)

    private val constraint = Seq(
      color.map(colorPredicate).toSeq,
      resident.map(residentPredicate).toSeq,
      pet.map(petPredicate).toSeq,
      drink.map(drinkPredicate).toSeq,
      tobacco.map(tobaccoPredicate).toSeq
    ).flatten

    def matches(house: House): Boolean = constraint.forall(predicate => predicate(house))
    def partialMatches(house: House): Boolean = constraint.exists(predicate => predicate(house)) && !matches(house)
  }

  lazy val solve: Solution = {
    val structuralConstraints = Seq(
      Constraint(color = Some(Red), resident = Some(Englishman)),
      Constraint(resident = Some(Spaniard), pet = Some(Dog)),
      Constraint(color = Some(Green), drink = Some(Coffee)),
      Constraint(resident = Some(Ukrainian), drink = Some(Tea)),
      Constraint(tobacco = Some(OldGold), pet = Some(Snails)),
      Constraint(color = Some(Yellow), tobacco = Some(Kools)),
      Constraint(tobacco = Some(LuckyStrike), drink = Some(Juice)),
      Constraint(resident = Some(Japanese), tobacco = Some(Parliaments))
    )

    def matches(h: House, selector: Constraint => (House => Boolean)) = {
      structuralConstraints.exists(c => selector(c)(h))
    }

    def shouldKeep(h: House): Boolean = {
      matches(h, _.matches) && !matches(h, _.partialMatches)
    }

    def residentWhere(solutions: Seq[Seq[House]], predicate: House => Boolean) = {
      solutions.head.find(predicate).map(_.resident).orNull
    }

    def allUnique(houses: House*): Boolean = {
      def unique[T](extractor: House => T): Boolean = {
        houses.map(extractor).distinct.lengthCompare(houses.length) == 0
      }

      unique(_.color) && unique(_.resident) && unique(_.drink) && unique(_.tobacco) && unique(_.pet)
    }

    def matchesPositionalConstraints(houses: House*): Boolean = {
      def isNextTo(item: House => Boolean, expectedNext: House => Boolean, rightOnly: Boolean = false) = {
        val index = houses.indexWhere(item)
        val expectedIndex = houses.indexWhere(expectedNext)
        if (index >= 0 && expectedIndex >= 0) {
          val distance = expectedIndex - index
          if (rightOnly) distance == -1 else math.abs(distance) == 1
        }
        else if (rightOnly) index < 0
        else true
      }

      Seq(
        allUnique(houses:_*),
        isNextTo(_.tobacco == Chesterfields, _.pet == Fox),
        isNextTo(_.tobacco == Kools, _.pet == Horse),
        isNextTo(_.color == Green, _.color == Ivory, rightOnly = true)
      ).forall(identity)
    }

    val problemSpace = for {
      color <- colors
      nationality <- residents
      pet <- pets
      drink <- drinks
      tobacco <- tobacco
      house = House(color, nationality, pet, drink, tobacco) if shouldKeep(house)
    } yield house

    val (firstHouse, rFirst) = problemSpace.partition(h => h.resident == Norwegian)
    val solution = for {
      first <- firstHouse.filter(h => h.color != Blue && h.drink != Milk) if matchesPositionalConstraints(first)

      (secondHouse, rSecond) = rFirst.partition(_.color == Blue)
      second <- secondHouse.filter(_.drink != Milk) if matchesPositionalConstraints(first, second)

      (thirdHouse, rThird) = rSecond.partition(_.drink == Milk)
      third <- thirdHouse if matchesPositionalConstraints(first, second, third)

      fourth <- rThird if matchesPositionalConstraints(first, second, third, fourth)

      fifth <- rThird if matchesPositionalConstraints(first, second, third, fourth, fifth)
    } yield Seq(first, second, third, fourth, fifth)

    Solution(
      residentWhere(solution, _.drink == Water),
      residentWhere(solution, _.pet == Zebra)
    )
  }
}

