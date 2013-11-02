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

case class JsonMember(name: String, value: Any)

class JsonParser extends JavaTokenParsers {
  def value: Parser[Any] = obj | arr | floatingPointNumber | stringLiteral | "null" | "true" | "false"
  def obj: Parser[List[JsonMember]] = "{" ~> members <~ "}"  
  def arr: Parser[List[Any]] = "[" ~> values <~ "]" 
  def member: Parser[JsonMember] = stringLiteral ~ ":" ~ value ^^ { case name ~ ":" ~ value => JsonMember(name, value) }
  def values: Parser[List[Any]] = repsep(value, ",")
  def members: Parser[List[JsonMember]] = repsep(member, ",")
}

object ParseJson extends JsonParser {

  def parseJson(string: String) = {
    parseAll(value, string)
  }
}