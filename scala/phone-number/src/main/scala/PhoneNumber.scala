object PhoneNumber {
  private val phoneNumber = """1?([2-9]\d{2}[2-9]\d{2}\d{4})""".r

  def clean(number: String): Option[String] = {
    number.replaceAll("\\D", "") match {
      case phoneNumber(n) => Some(n)
      case _ => None
    }
  }
}
