Scala Json Dsl 
==============

We are getting close to fully supporting the JSON spec.  We need to support JSON arrays.  Lets do it.

step5
-----

- Run JsonParserTest and verify the following error:

```
[2.29] failure: `false' expected but `[' found

    	    {"phone numbers" : [ 

    	                       ^
```

- Review the rules needed to implement JSON arrays:
 
```
arr ::= "[" [values] "]".
values ::= value {"," value}.
```

- No new concepts are needed to implement these parsers.  Go to it!

Hint:  Don't forget to add the "arr" parser to the value parser. 

- Re-run JsonParserTest and verify that it runs green.
