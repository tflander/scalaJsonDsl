package toddf.json

import scala.util.parsing.combinator.JavaTokenParsers

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

abstract class JsonElement
case class JsonString(name: String, value: String) extends JsonElement
case class JsonLong(name: String, value: Long) extends JsonElement
case class JsonNull(name: String) extends JsonElement
case class JsonBoolean(name: String, value: Boolean) extends JsonElement

class Json extends JavaTokenParsers {
  def value: Parser[Any] = obj | arr | floatingPointNumber ^^ (_.toLong) | stringLiteral ^^ (x => stripQuotes(x)) | "null" ^^ (x => null) | "true" ^^ (x => true) | "false" ^^ (x => false)
  def obj: Parser[Any] = "{" ~ members ~ "}"
  def arr: Parser[Any] = "[" ~ values ~ "]"
  def member: Parser[JsonElement] = stringLiteral ~ ":" ~ value ^^ {case name~":"~value => buildElement(name, value)}
  def values: Parser[Any] = repsep(value, ",")
  def members: Parser[Any] = repsep(member, ",")
  
  def stripQuotes(s: String): String = {
    require(s.startsWith("\"") && s.startsWith("\""))
    return s.substring(1, s.length - 1)
  }
  
  def buildElement(name: String, value: Any): JsonElement = {
    val realName = stripQuotes(name)
    value match {
      case s: String => return JsonString(realName, s)
      case n: Long => return JsonLong(realName, n)
      case b: Boolean => return JsonBoolean(realName, b)
      case null => return JsonNull(realName)
    }
  }
}

object ParseJson extends Json {

  def parseJson(string: String) = {
    parseAll(value, string)
  }
}