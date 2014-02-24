Scala Json Dsl 
==============

Now is the time to get arrays working.

This step introduces two variants to the tilde composition operator (~).

The normal composition operator includes both the left and right hand expression in both the parsing and the result.
The ~> and <~ operators are used to throw away part of the parsed expression when forming the parse result

```
P ~ Q  :==> composition operator to include both P and Q in the parse results
P ~> Q :==> composition operator to parse P and Q, then throw away P for the parse results
P <~ Q :==> composition operator to parse P and Q, then throw away Q for the parse results
```

step10
------

- Run JsonParserResultTest and verify the following error:

```
An exception or error caused a run to abort: (([~List(false, true, null, string, 123))~]) was not equal to List(false, true, null, string, 123) (JsonParserResultTest.scala:97)
```

We are really close to having the correct parse result.  The only problem is that our parse results include square brackets.  We
only need a list of JSON values.

- Note the defintion of the array parser and the use of the composition operator to include square brackets in the parsing 
of an array:

```
def arr: Parser[Any] = "[" ~ values ~ "]"
```

We don't need our parse results to include the square brackets. Replace the current composition operator with the appropriate 
composition operator to throw away the square brackets for the parse result.

- Re-run the test and verify that it is now green.