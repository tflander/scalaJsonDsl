Scala Json Dsl 
==============

We cheated in the step where we implemented the the "obj" parser for JSON objects.  The specification states that a JSON object
can contain multiple members, but we knew our tests only handled zero or one member.  Now we need to handle JSON objects with
zero to many members.

step4
-----
- Run JsonParserTest
- Verify Error:

```
[2.52] failure: `}' expected but `,' found

    	    {"address" : {"street" : "123 Main Street", 

    	                                              ^
```

- Update the "obj" parser to spec.  It's not a member or nothing between braces.  It's members between braces.

- Write the "memebers" parser.  Use the repsep() method to handle the interleaved repetition of members and comma separators

Example: `repsep(term, ",")` parses a comma-separated list of term's, yielding a list of these terms.

- Re-run the test and verify it now passes.    	                                              