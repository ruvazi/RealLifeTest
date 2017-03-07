# RealLifeTest
#####By Dennis Andersen

The original test exposed one test of a specific image and that it could load the image and get the licenseplate number,
the second test in the original test file then tested all the images in the resource folder by loading testparameters consisting of
a filename and the expected result from the test. The test passed as i did not take in to consideration which test that failed, but rather
counted how many passed.

So in order to get specific test data i created a new [test](https://github.com/tjaydk/RealLifeTest/blob/master/RecognitionAllIT.java)
which in the same manner as with the original test loaded the parameters from the file, but then ran parameterized test, which means
that a new specific test was run n times with a new test object each time, in order to see which of the images failed the test. Some of
them were because it did not interpret the O correctly as a letter, rather than a 0(Zero) letter value.

This type of data driven test i useful as it gives you the possibility to recieve test data in a text document, etc from external source, 
you dont have to write n amount of test for each different value and your test suite will be easier to maintain and to understand.
You could even split the files up into boundary tests and equivalence test files to make the test suites a bit more understandable.

This particular test is a JUnit test and not a unit test persay as multiple methods are called to excecute the test like:
recognize(), recognizePlate(), averagePlateHeight() etc. so its closer to integration/validation testing. To see if the software 
acts as expected.

Including the hamcrest assertion library by adding it to the pom.xml file made the output of the tests readable to logical, see image
below for output.
![Hamcrest output](https://camo.githubusercontent.com/01afdd222cbd15b0ebfa0a39ed1baf691c31913c/687474703a2f2f6936352e74696e797069632e636f6d2f7861756276372e706e67)
