package toddf.json
import org.scalatest._
import scala.util.parsing.combinator.JavaTokenParsers

class JsonParserTest extends FunSpec with ShouldMatchers {

  /*
from "Programming in Scala: A comprehensive step-by-step guide", 2nd edition 
Martin Odersky, Lex Spoon, Bill Venners
published by Artima

Here is the grammer for expressing JSON:

value ::= obj | arr | stringLiteral | �oatingPointNumber | "null" | "true" | "false".
member ::= stringLiteral ":" value.
arr ::= "[" [values] "]".
obj ::= "{" [members] "}".
members ::= member {"," member}.
values ::= value {"," value}.

   */

  class Json extends JavaTokenParsers {
    def value: Parser[Any] = "{}"
    def obj: Parser[Any] = "TODO: create lexical expression for parsing"
    def arr: Parser[Any] = "TODO: create lexical expression for parsing"
    def member: Parser[Any] = "TODO: create lexical expression for parsing"
    def values: Parser[Any] = "TODO: create lexical expression for parsing"
    def members: Parser[Any] = "TODO: create lexical expression for parsing"
  }

  object ParseJson extends Json {

    def parseJson(string: String) = {
      parseAll(value, string)
    }
  }

  import ParseJson._

  describe("JSON parsing tests") {
    it("parses empty Json") {
      val json = parseJson("{}")

      json.successful should be(true)
      println(json.get.toString) // prints "{}", but for now we are not going to worry about output
    }

    it("parses a single element") {
      val json = parseJson("""{"zip" : 48092}""")
      
      // TODO:  now that we cheated for the first test, let's do some real parsing
      json.successful should be(true)
      println(json.get.toString)
    }

  }

}