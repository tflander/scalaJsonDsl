Scala Json Dsl 
==============

For Step0, we cheated by matching "{}" with "{}".  This is not very useful.  Let's do some real parsing.

For now we are not going to worry about parsing the JSON into a useful object.  We will do that in later steps.
For now we just want to set up the parsing rules.

step 1
------

- Run JsonParserTest.
- verify error:
```
[1.1] failure: `{}' expected but `{' found

{"zip" : 48092}
^
```
Our cheap parse expression "{}" does not match a JSON name/number pair.

This is the single most difficult step of this kata.  Let's review our grammer rules to see how to parse a name/number pair.

```
value ::= obj | arr | stringLiteral | FloatingPointNumber | "null" | "true" | "false".
member ::= stringLiteral ":" value.
arr ::= "[" [values] "]".
obj ::= "{" [members] "}".
members ::= member {"," member}.
values ::= value {"," value}.
```

The element "value" can be an object, array, stringLiteral, floatingPointNumber, "null", "true", or "false".  For now we only
care about the name/number pair as an object, and the floatingPointNumber for the zip code.

- Update the "value" parser to look for a floatingPointNumber or something that the obj parser can consume.

Hint: floatingPointNumber is defined by our base class JavaTokenParsers

Hint: use the normal "or" operator | to specify alternative values

Hint: obj | floatingPointNumber

Hint: look at the solution in the step2 branch

- Now that we've referenced the "obj" parser in our "value" parser, we need to define it. We will need to replace the TODO, but 
  for now let's just note that a JSON object is a list of members inside of braces.  We will come back to obj shortly.
  
- Let's look ahead to the grammer rule for "member".  A member is a stringLiteral and value pair separated by a colon.  In our
  test example, the member is __"zip" : 48092__.
  
- Write the code to implement the member parser.  We will use the tilde operator (~) for sequential composition.  A member is
  the sequence of a stringLiteral, colon, and value.
  
Hint:  stringLiteral ~ ":" ~ value
  
- Let's go back to "obj" parser.  We eventually have to support a list of members, but for now let's define the parser to be an
  optional member surrounded by braces.  We can use the tilde for sequential composition and the pipe for alternative values. 
  We can group our alternative values using parenthesis.
  
Hint: "{" ~ (member | "") ~ "}"

- Run the test and (hopefully) verify that it is green.  