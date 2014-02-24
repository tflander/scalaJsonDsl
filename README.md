Scala Json Dsl 
==============

Now that we are getting appropriate parse results for JSON arrays, we need to do the same for JSON objects.

This is the last step.  Step 12 is provided only if you need to refer to it for the solution.

step11
------
- Run JsonParserResultTest and verify three errors.

```
- handles a single element *** FAILED ***
  (({~List(JsonString(name,value)))~}) was not equal to List(JsonString(name,value)) (JsonParserResultTest.scala:101)
- handles multiple elements *** FAILED ***
  (({~List(JsonString(name,value), JsonString(name1,value1)))~}) was not equal to List(JsonString(name,value), JsonString(name1,value1)) (JsonParserResultTest.scala:113)
- handles complex objects *** FAILED ***
  scala.MatchError: List(555-1212, 655-1268) 
```

The first two errors are because the braces are being included in the parse results.  Let's fix these first.

- Modify the obj parser to throw away the braces in the parse results.

- Re-run JsonParserResultTest to verify that we fixed two of three errors.  Verify one remaining error:

- handles complex objects *** FAILED ***
```
  scala.MatchError: List(555-1212, 655-1268) (of class scala.collection.immutable.$colon$colon)
  at toddf.json.Json.buildElement(JsonParser.scala:43)
  ```
  
  Our matcher does not handle the case where we get a list of values for a JSON array.
  
- Add a matcher to convert a list of type Any to a JsonList 

- Verify all the tests run green.  

- You are done!  Congratulations!!!
