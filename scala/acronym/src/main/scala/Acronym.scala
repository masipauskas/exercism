object Acronym {
  private val acronym = """\b\w""".r
  def abbreviate(phrase: String): String = {
    acronym
      .findAllIn(phrase)
      .map(_.toUpperCase())
      .mkString("")
  }
}
