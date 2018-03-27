import ForthError.ForthError

import scala.util.parsing.combinator.RegexParsers

object ForthError extends Enumeration {
  type ForthError = Value
  val DivisionByZero, StackUnderflow, InvalidWord, UnknownWord, UnknownError = Value
}

case class ForthEvaluatorState(stack: List[AST], aliases: List[UserDefined] = Nil) {
  override def toString: String = stack.mkString(" ")
}

sealed trait AST
case class Digit(n: Int) extends AST {
  override def toString: String = n.toString
}

case class Symbol(symbol: String) extends AST {
  override def toString: String = symbol
}

case class UserDefined(alias: String, ast: List[AST]) extends AST {
  override def toString: String = ast.mkString(" ")
}

trait ForthEvaluator extends ForthParser {
  def eval(text: String): Either[ForthError, ForthEvaluatorState] = {
    parse(text)
      .map { ast =>
        val aliases = ast.collect { case a: UserDefined => a }
        ForthEvaluatorState(ast.filterNot(aliases.contains), aliases)
      }
      .flatMap(evaluate)
      .map(r => r.copy(r.stack.reverse))
  }

  def evaluate(state: ForthEvaluatorState): Either[ForthError, ForthEvaluatorState] = state.stack match {
    case Symbol(s) :: rest if state.aliases.exists(_.alias == s) =>
      val ast = state.aliases.find(_.alias == s).map(_.ast).get
      evaluate(state.copy(ast ::: rest))

    case Symbol(s1 @ ("+" | "-" | "*" | "/")) :: Digit(b) :: Symbol(s2 @ ("+" | "-" | "*" | "/")) :: rest =>
      evaluate(state.copy(Symbol(s2) :: rest)).flatMap { r =>
        evaluate(r.copy(Symbol(s1) :: Digit(b) :: r.stack))
      }
    case Symbol(s @ ("+" | "-" | "*" | "/")) :: Digit(b) :: Digit(a) :: rest  => s match {
      case "+" => evaluate(state.copy(Digit(a + b) :: rest))
      case "-" => evaluate(state.copy(Digit(a - b) :: rest))
      case "*" => evaluate(state.copy(Digit(a * b) :: rest))
      case "/" if b != 0 => evaluate(state.copy(Digit(a / b) :: rest))
      case "/" => Left(ForthError.DivisionByZero)
    }
    case Symbol("swap") :: Digit(a) :: rest =>
      evaluate(state.copy(rest)).map(r => r.copy(r.stack.head :: Digit(a) :: r.stack.tail))
    case Symbol("over") :: v1 :: v2 :: rest =>
      evaluate(state.copy(v1 :: v2 :: rest)).map(r => r.copy(r.stack.tail.head :: r.stack.head :: r.stack.tail.head :: r.stack.drop(2)))
    case Symbol("drop") :: rest =>
      evaluate(state.copy(rest)).map { r => r.copy(r.stack.tail) }
    case Symbol("dup") :: rest =>
      evaluate(state.copy(rest)).flatMap { r =>
        evaluate(r.copy(r.stack.head :: r.stack))
      }

    case Digit(_) :: _ => Right(state)
    case _ => Left(ForthError.InvalidWord)
  }
}

trait ForthParser extends RegexParsers {
  val digit: Parser[AST] = """\d+""".r ^^ { digit => Digit(digit.toInt) }
  val variable: Parser[String] = ("""[\w-]+""".r | """[+-/\*]""".r) ^^ { v => v }
  val symbol: Parser[AST] = variable ^^ { s => Symbol(s) }
  val keyword: Parser[AST] = digit | symbol
  val userDefined: Parser[AST] = ":" ~ variable ~ rep(keyword) ~ ";" ^^ {
    case _ ~ alias ~ ast ~ _ => UserDefined(alias, ast.reverse)
  }

  val all: Parser[List[AST]] = rep(userDefined | keyword)

  def parse(text: String): Either[ForthError, List[AST]] = parseAll(all, text.toLowerCase()) match {
    case Success(ast, _) => Right(ast.reverse)
    case NoSuccess(f, s) =>
      Left(ForthError.UnknownWord)
  }
}