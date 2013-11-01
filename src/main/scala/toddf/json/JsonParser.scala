package toddf.json

import scala.util.parsing.combinator.JavaTokenParsers

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