Scala Json Dsl 
==============

In the last step we implemented one type of JsonElement to represent string values.  Let's implement the other types of 
JsonElement.

step9
-----

- Review changes to JsonParser.  Note we have some new case classes to represent all of the types of JsonElement.

```
abstract class JsonElement
case class JsonString(name: String, value: String) extends JsonElement
case class JsonLong(name: String, value: Long) extends JsonElement
case class JsonNull(name: String) extends JsonElement
case class JsonBoolean(name: String, value: Boolean) extends JsonElement
```

- Note the code that implements JsonString:

```
  def buildElement(name: String, value: Any): JsonElement = {
    value match {
      case s: String => return JsonString(stripQuotes(name), s)
    }
  }
```

We have a matcher to handle the case where the value is a string, but we don't match the other cases yet.

- Run JsonParserResultTest and verify the MatchError for the cases where we don't handle the value type.

- Implement the missing value types and verify the tests now run green.

