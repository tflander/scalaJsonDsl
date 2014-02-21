package toddf.json
import org.scalatest._
import scala.util.parsing.combinator.JavaTokenParsers

class JsonParserTest extends FunSpec with ShouldMatchers {

  /*
from "Programming in Scala: A comprehensive step-by-step guide", 2nd edition 
Martin Odersky, Lex Spoon, Bill Venners
published by Artima

Here is the grammer for expressing JSON:

value ::= obj | arr | stringLiteral | floatingPointNumber | "null" | "true" | "false".
member ::= stringLiteral ":" value.
arr ::= "[" [values] "]".
obj ::= "{" [members] "}".
members ::= member {"," member}.
values ::= value {"," value}.

   */

  class Json extends JavaTokenParsers {
    def value: Parser[Any] = obj | floatingPointNumber | stringLiteral | "null" | "true" | "false"
    def obj: Parser[Any] = "{" ~ members ~ "}"
    def arr: Parser[Any] = "TODO: create lexical expression for parsing"
    def member: Parser[Any] = stringLiteral ~ ":" ~ value
    def values: Parser[Any] = "TODO: create lexical expression for parsing"
    def members: Parser[Any] = repsep(member, ",")
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
    }

    it("parses a numeric value") {
      val json = parseJson("""{"zip" : 48092}""")
      json.successful should be(true)
    }

    it("parses a string value") {
      val json = parseJson("""{"name" : "todd"}""")
      json.successful should be(true)
    }

    it("parses a null value") {
      val json = parseJson("""{"name" : null}""")
      json.successful should be(true)
    }

    it("parses a boolean true value") {
      val json = parseJson("""{"cool" : true}""")
      json.successful should be(true)
    }

    it("parses a boolean false value") {
      val json = parseJson("""{"cool" : false}""")
      json.successful should be(true)
    }
  }

  describe("free whitespace tests") {
    it("doesn't require spaces around the colon") {
      val json = parseJson("""{"name":"todd"}""")
      json.successful should be(true)
    }

    it("allows line breaks and tabs") {
      val json = parseJson("""{"name":
    		  						"todd"}""")

      json.successful should be(true)
    }
  }

  describe("Json Objects and Arrays") {
    
    it("parses a standard key-value pair object") {
    	val json = parseJson("""
    	    {"address" : {"street" : "123 Main Street", 
    					  "city"   : "Springfield",
    					  "state"  : "California",
    					  "zip"    : "90210" 
    					}
    		}
    	""")
    	
    	json.successful should be(true)
    }
    
    it("parses an array") {
    	val json = parseJson("""
    	    {"phone numbers" : [ 
    					  "555-1212",
    					  "655-8629"
    					]
    		}
    	""")
    	
    	println(json)
    	json.successful should be(true)
    }
    
  }

}