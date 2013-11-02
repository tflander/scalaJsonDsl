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

abstract class JsonElement
case class JsonString(name: String, value: String) extends JsonElement
case class JsonLong(name: String, value: Long) extends JsonElement
case class JsonNull(name: String) extends JsonElement
case class JsonBoolean(name: String, value: Boolean) extends JsonElement
case class JsonList(name: String, value: List[Any]) extends JsonElement

class JsonParser extends JavaTokenParsers {
  def value: Parser[Any] = obj | arr | floatingPointNumber | stringLiteral | "null" | "true" | "false"
  def obj: Parser[List[JsonElement]] = "{" ~> members <~ "}"
  def arr: Parser[List[Any]] = "[" ~> values <~ "]"
  def member: Parser[JsonElement] = stringLiteral ~ ":" ~ value ^^ { case name ~ ":" ~ value => buildJsonElement(name, value) }
  def values: Parser[List[Any]] = repsep(value, ",")
  def members: Parser[List[JsonElement]] = repsep(member, ",")

  def buildJsonElement(name: String, value: Any): JsonElement = {
    require(name.startsWith("\"") && name.endsWith("\""), "Json requires element names to be wrapped in quotes.  Found: " + name)
    val realName = name.substring(1, name.length - 1)
    value match {
      
      case x: String => {
        buildValue(x) match {
          case true => return JsonBoolean(realName, true)
          case false => return JsonBoolean(realName, false)
          case v: Long => return JsonLong(realName, v)
          case s: String => return JsonString(realName, s)
          case null => return JsonNull(realName)
        }
      }
      case x: List[Any] => return JsonList(realName, x)
    }
  }
  
  def buildValue(raw: String): Any = {
    return raw match {
        case "true" =>  return true
        case "false" =>  return false
        case "null" => return null
        case x: String if x.startsWith("\"") && x.endsWith("\"") => return x.substring(1, x.length - 1)
        case x: String => return x.toLong
    }
  }
}

object ParseJson extends JsonParser {

  def parseJson(string: String) = {
    parseAll(value, string)
  }
}