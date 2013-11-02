package toddf.json

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.combinator.Parsers

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

class JsonThing()

case class JsonValue(huh: Any) extends JsonThing {
  println("JsonValue = " + huh + " (" + huh.getClass.getName + ")")
}

case class JsonObject(huh: Any) extends JsonThing {
  println("JsonObject = " + huh + " (" + huh.getClass.getName + ")")
}

case class JsonArray(huh: Any) extends JsonThing {
  println("JsonArray = " + huh + " (" + huh.getClass.getName + ")")
}

case class JsonMember(name: String, value: Any) extends JsonThing {
  println("JsonMember name = " + name)
  println("JsonMember value = " + value)
}

case class JsonValues(huh: Any) extends JsonThing {
  println("JsonValues = " + huh + " (" + huh.getClass.getName + ")")
}

case class JsonMembers(huh: Any) extends JsonThing {
  println("JsonMembers = " + huh + " (" + huh.getClass.getName + ")")
}

class JsonParser extends JavaTokenParsers {
  def value: Parser[Any] = obj | arr | floatingPointNumber | stringLiteral | "null" | "true" | "false" ^^ (JsonValue(_))
  def obj: Parser[JsonObject] = "{" ~> members <~ "}"  ^^ (JsonObject(_))
  def arr: Parser[JsonArray] = "[" ~> values <~ "]" ^^ (JsonArray(_))
  def member: Parser[JsonMember] = stringLiteral ~ ":" ~ value ^^ { case name ~ ":" ~ value => JsonMember(name, value) }
  def values: Parser[JsonValues] = repsep(value, ",") ^^ (JsonValues(_))
  def members: Parser[JsonMembers] = repsep(member, ",") ^^ (JsonMembers(_))
}

object ParseJson extends JsonParser {

  def parseJson(string: String) = {
    parseAll(value, string)
  }
}