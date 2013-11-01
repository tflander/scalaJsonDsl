package toddf.json
import org.scalatest._
import scala.util.parsing.combinator.JavaTokenParsers

class JsonParserTest extends FunSpec with ShouldMatchers {

  /*
from "Programming in Scala: A comprehensive step-by-step guide", 2nd edition 
Martin Odersky, Lex Spoon, Bill Venners
published by Artima

Here is the grammer for expressing JSON:

value ::= obj | arr | stringLiteral | ßoatingPointNumber | "null" | "true" | "false".
member ::= stringLiteral ":" value.
arr ::= "[" [values] "]".
obj ::= "{" [members] "}".
members ::= member {"," member}.
values ::= value {"," value}.

   */

  class Json extends JavaTokenParsers {
    def value: Parser[Any] = obj | floatingPointNumber
    def obj: Parser[Any] = "{" ~ (member | "") ~ "}"
    def arr: Parser[Any] = "TODO: create lexical expression for parsing"
    def member: Parser[Any] = stringLiteral~":"~value
    def values: Parser[Any] = "TODO: create lexical expression for parsing"
    def members: Parser[Any] = "TODO: create lexical expression for parsing"
  }

  object ParseJson extends Json {

    def parseJson(string: String) = {
      parseAll(value, string)
    }
  }

  import ParseJson._

  describe("single element parsing") {
    
    it("parses empty Json") {
      val json = parseJson("{}")

      json.successful should be(true)
      println(json.get.toString) // prints "(({~List())~})", but for now we are not going to worry about output
    }

    it("parses a numeric value") {
      val json = parseJson("""{"zip" : 48092}""")
      
      json.successful should be(true)
      println(json.get.toString)  // prints "(({~List((("zip"~:)~48092)))~})", but for now we are not going to worry about output
    }

    // TODO:  get the rest of the tests to pass per the JSON grammer for value.
    
    it("parses a string value") {
      val json = parseJson("""{"name" : "todd"}""")
      
      json.successful should be(true)
      println(json.get.toString)
    }
    
    it("parses a null value") {
      val json = parseJson("""{"name" : null}""")
      
      json.successful should be(true)
      println(json.get.toString)
    }
    
    it("parses a boolean true value") {
      val json = parseJson("""{"cool" : true}""")
      
      json.successful should be(true)
      println(json.get.toString)
    }
    
    it("parses a boolean false value") {
      val json = parseJson("""{"cool" : false}""")
      
      json.successful should be(true)
      println(json.get.toString)
    }
  }

}