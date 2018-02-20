import org.scalatest.{Matchers, FunSuite}

/** @version 2.0.0 */
class CryptoSquareTest extends FunSuite with Matchers {

  test("Lowercase") {
    CryptoSquare.normalizedPlaintext("Hello") should be ("hello")
  }

  test("Remove spaces") {
    CryptoSquare.normalizedPlaintext("Hi there") should be ("hithere")
  }

  test("Remove punctuation") {
    CryptoSquare.normalizedPlaintext("@1, 2%, 3 Go!") should be ("123go")
  }

  test("empty plaintext results in an empty rectangle") {
    CryptoSquare.plaintextSegments("") should be (List())
  }

  test("4 character plaintext results in an 2x2 rectangle") {
    CryptoSquare.plaintextSegments("Ab Cd") should be (List("ab", "cd"))
  }

  test("9 character plaintext results in an 3x3 rectangle") {
    CryptoSquare.plaintextSegments("This is fun!") should be (List("thi", "sis", "fun"))
  }

  test("54 character plaintext results in an 8x7 rectangle") {
    CryptoSquare.plaintextSegments("If man was meant to stay on the ground, god would have given us roots.") should be (List("ifmanwas", "meanttos", "tayonthe", "groundgo", "dwouldha", "vegivenu", "sroots"))
  }

  test("empty plaintext results in an empty encode") {
    CryptoSquare.encoded("") should be ("")
  }

  test("Non-empty plaintext results in the combined plaintext segments") {
    CryptoSquare.encoded("If man was meant to stay on the ground, god would have given us roots.") should be ("imtgdvsfearwermayoogoanouuiontnnlvtwttddesaohghnsseoau")
  }

  test("empty plaintext results in an empty ciphertext") {
    CryptoSquare.ciphertext("") should be ("")
  }

  test("9 character plaintext results in 3 chunks of 3 characters") {
    CryptoSquare.ciphertext("This is fun!") should be ("tsf hiu isn")
  }

  test("54 character plaintext results in 7 chunks, the last two padded with spaces") {
    CryptoSquare.ciphertext("If man was meant to stay on the ground, god would have given us roots.") should be ("imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn  sseoau ")
  }
}