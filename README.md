Scala Json Dsl 
==============

We've refactored.  Note that JsonParserTest no longer has our JSON parser.  Our code is in JsonParser.scala.

We now have a new test suite called JsonParserResultTest.  Let's look at this new test

step7
-----
- Review JsonParserResultTest.

Note a bit of trickery on line 9.  I wanted to be able to use my JSON parser to test-drive the various values supported by the 
JSON spec.  I was offended by the idea of calling parseJson("123") to test the result for the numeric value 123.  The string 
"123" is not JSON.  No problem.  One of the beauties of Scala is that it is easy to assign names to things.  I prefer to use the
name "resolveValue" rather than "parseJson".

Scala parsers perform result conversion by using the eyebrows operator (^^).  You supply a function to the right-hand side of 
this operator.  The function takes the parsed out string and returns the thing you want.

Examples:
  floatingPointNumber ^^ (_.toLong) 
  "false" ^^ (x => false)
  stringLiteral ^^ (x => foo(x) )

The first example passes the String.toLong() method to perform the parse result.  The underscore is a placeholder for the string
returned by the parser.

The second example passes a function defined using the hash-rocket (=>).  The left-hand-side represents the string returned by 
the parser.  The right-hand-side is the boolean value returned when parsing the string "false".

The third example is like the second, but this time we are using the left-hand-side string by passing it to a function.

- Run JsonParserResultTest.  Verify a whopping five errors.  Don't panic.  Fix them one-at-a-time by adding the eyebrows operator
  to the appropriate alternatives in the value parser.
  
- Fix and Re-run until everything is green.