Scala Json Dsl 
==============

final
-----

This is a spike after I gave up on step 8

step8
-----

Done -- Cheat to fix broken test

Goal -- Spike final parser result
        
Todo -- Start over and do step 8 right


step7
-----
Done -- refactored to move the parser from the test code to production source dir
        created new test for refining the parser output
        
Goal -- Review refactoring and new test class JsonParserResultTest

Todo -- Review JsonParserResultTest and fix broken test.
        
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
