Scala Json Dsl 
==============

We are going to work on the result of parsing a member.  A member is essentially a JSON name/value pair.

step8
-----

- Review the new section at the bottom of JsonParserResultTest.  Note the nested Scala singleton NameValueParser.

Normally I would take this approach when creating a DSL from scratch.  It makes sense to design a DSL from the top down,
then build it from the bottom up.  You may not know your domain when you start designing.  That's OK.  Start with what you
know.  Keep in mind that, like an API, a DSL may become difficult to change once people start using it -- but don't let
that stop you from starting.


- Review the member parser in JsonPaser:

```
  def member: Parser[Any] = stringLiteral ~ ":" ~ value
``

We currently define the member parser to return a parser of type Any.  Basically, we are saying that we don't care what the 
parser returns.  Let's change that.

- Review new code in JsonParser.scala

```
abstract class JsonElement
case class JsonString(name: String, value: String) extends JsonElement
```

We would like our member parser to return an object of type JsonElement.  If the value in the name/value pair is of type
String, we want to return a object of the specific type JsonString.

- Run JsonParserResultTest and note the following error:
```
  (("name"~:)~value) was not equal to JsonString(name,value)
```

The default parse result is not only difficult for humans to read.  It's also difficult for systems to utilize.

- Add the eyebrows operator to the member parser to return the object we want.

- Verify that test runs green.

