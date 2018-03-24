import java.time.LocalDate

import LensPerson.{Address, Born, EpochDay, Person}
import monocle.PLens
import monocle.Lens
import monocle.macros.GenLens

object LensPerson {
  import Lenses._
  case class Person(_name: Name, _born: Born, _address: Address)

  case class Name(_foreNames: String /*Space separated*/ , _surName: String)

  // Value of java.time.LocalDate.toEpochDay
  type EpochDay = Long

  case class Born(_bornAt: Address, _bornOn: EpochDay)

  case class Address(_street: String, _houseNumber: Int,
    _place: String /*Village / city*/ , _country: String)

  // Valid values of Gregorian are those for which 'java.time.LocalDate.of'
  // returns a valid LocalDate.
  case class Gregorian(_year: Int, _month: Int, _dayOfMonth: Int)

  // Implement these.

  val bornStreet: Born => String = bornOnStreet.asGetter.get

  val setCurrentStreet: String => Person => Person = currentStreet.asSetter.set

  val setBirthMonth: Int => Person => Person = { (m: Int) =>
    val func = (d: EpochDay) => LocalDate.ofEpochDay(d).withMonth(m).toEpochDay
    bornDate.modify(func)
  }

  // Transform both birth and current street names.
  val renameStreets: (String => String) => Person => Person = (func: String => String) => {
    currentStreet.modify(func).andThen((born composeLens bornOnStreet).modify(func))
  }
}

object Lenses {
  val street: Lens[Address, String] = GenLens[Address](_._street)

  val bornAt: Lens[Born, Address] = GenLens[Born](_._bornAt)

  val bornOn: Lens[Born, EpochDay] = GenLens[Born](_._bornOn)

  val born: Lens[Person, Born] =  GenLens[Person](_._born)

  val address: Lens[Person, Address] = GenLens[Person](_._address)

  val currentStreet: PLens[Person, Person, String, String] = address composeLens street

  val bornOnStreet: PLens[Born, Born, String, String] = bornAt composeLens street

  val bornDate: PLens[Person, Person, EpochDay, EpochDay] = born composeLens bornOn
}
