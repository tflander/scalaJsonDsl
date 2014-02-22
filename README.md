Scala Json Dsl 
==============

Congratulations!  You've done it!  You've built a JSON parser.

Just for fun, there is a new test added to verify the parser works for complex JSON.

We still have a couple of problems.  The biggest problem is that this parser does not produce any useful output.  We would like
to parse out the JSON into some useful object graph.  The second problem is that we've spiked out all of our parsing code in our
test class.  Our parser is not avallable for use for production code.

Since this is not a refactoring kata, we will perform the refactoring by checking out step7.  This will allow us to test drive
our parse results using a brand new test suite.  I would not normally separate my parsing and result processing in real life.  
I did so for this kata in order to get through the parsing without having to introduce too many concepts all at once.
 
step6
-----

- Review the test and verify that it runs green.

- Check out step7 to perform the refactoring.