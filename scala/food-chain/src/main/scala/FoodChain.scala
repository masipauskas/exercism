object FoodChain {
  private val animals = IndexedSeq(
    Animal("fly", "I don't know why she swallowed the fly. Perhaps she'll die."),
    Animal("spider", "It wriggled and jiggled and tickled inside her.", Some("spider that wriggled and jiggled and tickled inside her")),
    Animal("bird", "How absurd to swallow a bird!"),
    Animal("cat", "Imagine that, to swallow a cat!"),
    Animal("dog", "What a hog, to swallow a dog!"),
    Animal("goat", "Just opened her throat and swallowed a goat!"),
    Animal("cow", "I don't know how she swallowed a cow!"),
    Animal("horse", "She's dead, of course!", terminal = true)
  )
  def recite(from: Int, to: Int): String = {
    val verses = for { v <- from to to } yield verse(v)
    verses.mkString("", "\n", "\n")
  }

  private def verse(v: Int): String = {
    def firstAnimal(animal: Animal) = s"I know an old lady who swallowed a ${animal.name}.\n${animal.comment}\n"
    def rest(prior: Animal, current: Animal) = s"She swallowed the ${prior.name} to catch the ${current.aliasOrName}.\n"
    val chain = animals.take(v).reverse
    if (chain.head.terminal || chain.length == 1) firstAnimal(chain.head)
    else {
      val (verses, _) = chain.foldLeft(("", chain.head)) { case ((verse, prior), current) =>
        if (current == prior) (firstAnimal(current), current)
        else (verse + rest(prior, current), current)
      }

      verses + animals.head.comment + "\n"
    }
  }
}

case class Animal(name: String, comment: String, alias: Option[String] = None, terminal: Boolean = false) {
  def aliasOrName = alias.getOrElse(name)
}