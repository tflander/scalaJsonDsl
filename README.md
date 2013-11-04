Scala Json Dsl 
==============

Step12
------
Done -- Json objects now implemented

Todo -- build a parser to rule the world

step11
------
Done -- implement arrays

Goal -- implement objects

Todo -- fix broken tests in JsonParserResultTest

step10
------
Done -- finished implementing all types of JsonElement

Goal -- get arrays working

Todo -- fix broken test in JsonParserResultTest

step9
-----
Done -- fixed test to create a JsonString

Goal -- implement other flavors of JsonElement

Todo -- fix broken tests in JsonParserResultTest

step8
-----

Done -- fixed tests for resolving simple values

Goal -- resolve member parsing of a name value pair of String to String

Todo -- Fix broken test in JsonParserResultTest

step7
-----
Done -- refactored to move the parser from the test code to production source dir
        created new test for refining the parser output
        
Goal -- resolve simple values to the correct types, rather than returning parsed out strings

Todo -- Review JsonParserResultTest and fix broken tests.
        
step6
-----
Done -- implemented parsing for arrays & put it all together with a bigger example

Goal -- Refactor for next step

Todo -- check out step7 (the refactored code base)

step5
-----
Done -- implemented parsing for objects

Goal -- parse arrays

Todo -- Review JsonParserTest and fix broken test.

step4
-----
Done -- added free tests

Goal -- implement objects

Todo -- Review JsonParserTest and fix broken test.

step3
-----
Done -- finished parsing value

Goal -- understand whitespace is handled for you

Todo -- Review new tests that ran green with no code modifications

step2
-----
Done -- did some real parsing

Goal -- complete the parsing rules for value according to the JSON grammer

Todo -- Fix broken tests in JsonParserTest.

step 1
------
Done -- Cheated to get the test to pass

Goal -- do some real parsing

Todo -- Review JsonParserTest and fix broken test.


step 0
------
Done -- Created new scala project, configured the sbt-eclipse plug-in and updated scalatest version.

Goal -- become familiar with the problem we are trying to solve

Todo -- Review JsonParserTest and fix broken test.

Notes
------
This project has branches step0 through stepN.  These branches form a step-by-step kata for test driving a scala-json parser.

adopted from "Programming in Scala: A comprehensive step-by-step guide", 2nd edition 
Martin Odersky, Lex Spoon, Bill Venners.
published by Artima
