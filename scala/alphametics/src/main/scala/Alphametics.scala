import Parser.{Add, Constant, Equals, Number}

import scala.collection.parallel.ParSeq
import scala.util.parsing.combinator.RegexParsers

object Alphametics {
  def solve(formula: String): Option[Map[Char, Int]] = {
    val result = for {
      query <- Parser(formula).toSeq
      (index, candidates) = solutions(query)
      solution <- candidates
      if isValidSolution(query, index)(solution)
    } yield index.mapValues(solution)

    result.headOption
  }

  def isValidSolution(equals: Equals, index: Map[Char, Int])(data: IndexedSeq[Int]): Boolean = {
    def value(letters: Seq[Char]): Int = {
      val idx = letters.length - 1 to 0 by -1

      letters.zip(idx).foldLeft(0) { case (acc, (letter, pow)) =>
        acc + data(index(letter)) * math.pow(10, pow).toInt
      }
    }

    def number(n: Number): Int = n match {
      case l: Constant => value(l.letters)
      case a: Add => number(a.left) + number(a.right)
    }

    number(equals.left) == number(equals.right)
  }

  def solutions(equals: Equals): (Map[Char, Int], Stream[IndexedSeq[Int]]) = {
    def letters(number: Number): Seq[Char] = number match {
      case l: Constant => l.letters
      case a: Add => letters(a.left) ++ letters(a.right)
    }

    def leading(numeric: Number): Set[Char] = numeric match {
      case l: Constant => Set(l.letters.head)
      case a: Add => leading(a.left) ++ leading(a.right)
    }

    val availableLetters = (letters(equals.left) ++ letters(equals.right)).distinct
    val leadingLetters = leading(equals.left) ++ leading(equals.right)

    val leadingLetterIndexes = availableLetters
      .zipWithIndex.filter{ case (letter, _) => leadingLetters(letter) }
      .map(_._2)

    if (availableLetters.lengthCompare(10) > 0) (Map.empty, Stream.empty)
    else {
      (availableLetters.zipWithIndex.toMap,
        permutations(availableLetters.length).filterNot(p => leadingLetterIndexes.exists(idx => p(idx) == 0)))
    }
  }

  private def permutations(n: Int): Stream[IndexedSeq[Int]] = {
    if (n < 9) {
      (0 to 9).combinations(n).toStream
        .flatMap { seq =>
          if (seq.distinct == seq) seq.permutations.toSeq
          else Nil
        }
    } else precomputedPermutations
  }

  private lazy val precomputedPermutations = (0 to 9).permutations.toStream
}


object Parser extends RegexParsers {
  sealed trait Number
  case class Constant(letters: Seq[Char]) extends Number
  case class Add(left: Number, right: Number) extends Number
  case class Equals(left: Number, right: Number)

  def letter: Parser[Constant] = """\w+""".r ^^ { number => Constant(number.toUpperCase()) }
  def op: Parser[Number] = rep(letter <~ "+") ~ letter ^^ {
    case ops ~ last =>
    ops.foldLeft[Number](last) { case (acc, v) => Add(acc, v)}
  }
  def eq: Parser[Equals] = op ~ "==" ~ letter ^^ { case left ~ _ ~ right => Equals(left, right) }

  def apply(input: String): Option[Equals] = parseAll(eq, input) match {
    case Success(result, _) => Some(result)
    case f : NoSuccess => None
  }
}