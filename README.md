Scala Json Dsl 
==============

Let's pretend that Scala does not already let you use JSON directly in code.  We are going to test-drive a JSON parser 
from scratch.  This parser will allow us to embed JSON in our Scala code.

step 0
------

- Build and run tests with sbt (sbt test)

- Verify test failure:
```
   [error] Failed tests:
   [error] toddf.json.JsonParserTest
```
   
- Verify test error by running in eclipse:
```
   [1.1] failure: `TODO: create lexical expression for parsing' expected but `{' found

   {}
   ^
```   
- Review the test.  Note the lexical grammer for JSON in the comment expressed with six grammer rules:

```
value ::= obj | arr | stringLiteral | FloatingPointNumber | "null" | "true" | "false".
member ::= stringLiteral ":" value.
arr ::= "[" [values] "]".
obj ::= "{" [members] "}".
members ::= member {"," member}.
values ::= value {"," value}.
```

- Note the recursive nature of JSON.  Traditionally parsing tools force the programmer to handle recursion manually.
  Fortunately, life is much easier in Scala.
  
- Note that our class JSON (our JSON parser) is nested within our test class.  We will move our parser to production code
  in a future step.  This parser is actually a stub for six parsers, one for each grammer rule.

- Note we also have a Scala singleton ParseJson nested within our test class.  This singleton acts as the public interface
  for our parser.  Our public interface calls the "value" parser from the class JSON.
  
- Fix the failing test.  The parser consumes "{}" and tries to match it against "TODO: create lexical expression for parsing".
  This is a mis-match.  What is the simplest expression that matches "{}"?  Replace the TODO with that expression.
      
Notes
------
This project has branches step0 through stepN.  These branches form a step-by-step kata for test driving a scala-json parser.

adopted from "Programming in Scala: A comprehensive step-by-step guide", 2nd edition 
Martin Odersky, Lex Spoon, Bill Venners.
published by Artima
