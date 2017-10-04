import Bob.Phrases.{Question, Statement, Yell}

object Bob {
  def response(statement: String): String = statement.trim match {
    case answer @ (Yell(_) | Question(_) | Statement(_)) => answer
    case _ => "Whatever."
  }

  object Phrases {
    object Statement {
      def unapply(arg: String): Option[String] = if (arg.isEmpty) Some("Fine. Be that way!") else None
    }

    object Yell {
      def unapply(arg: String): Option[String] = if (arg.forall(_.isUpper)) Some("Whoa, chill out!") else None
    }

    object Question {
      def unapply(arg: String): Option[String] = if (arg.endsWith("?")) Some("Sure.") else None
    }

  }
}
