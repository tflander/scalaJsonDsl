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
    def value: Parser[Any] = obj | arr | floatingPointNumber | stringLiteral | "null" | "true" | "false"
    def obj: Parser[Any] = "{" ~ members ~ "}"
    def arr: Parser[Any] = "[" ~ values ~ "]"
    def member: Parser[Any] = stringLiteral ~ ":" ~ value
    def values: Parser[Any] = repsep(value, ",")
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
      println(json.get.toString) // prints "(({~List())~})", but for now we are not going to worry about output
    }

    it("parses a numeric value") {
      val json = parseJson("""{"zip" : 48092}""")

      json.successful should be(true)
      println(json.get.toString) // prints "(({~List((("zip"~:)~48092)))~})", but for now we are not going to worry about output
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
  
  describe("Putting it all together") {
     it("parses all combinations of a Json object") {
       val json = parseJson("""
            {"address" : {"street" : "123 Main Street", 
    					  "city"   : "Springfield",
    					  "state"  : "California",
    					  "zip"    : "90210",
    		   			  "phone numbers" : [
    		   								 "555-1212",
    		   								 "655-1268"
    		   			  					]
    					}
    		}
           """)
     }
  }

}