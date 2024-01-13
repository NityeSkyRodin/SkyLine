# SkyLine - A Java test framework

## Introduction
Skyline is a Java test framework that allows you to write tests in a simple and intuitive way. It is based on the JUnit framework and uses the Hamcrest library for assertions.
This is not a replacement for JUnit, but rather a wrapper that makes it easier to write tests.
In particular, it allows for more tests including, Unittests, Integrationtests and End-to-End tests.

## Getting started
To use Skyline in your project, you need to add the maven dependency to your pom.xml file.
This can be found at the following location: MAVEN CENTRAL LINK
Than you can start writing your tests.
It is as simple as that.

### Prerequisites
To use Skyline you need to have the following installed:
* Java 19 or higher
* Maven 3.6.3 or higher


## Usage
To use Skyline you need to create a test class and annotate it with one of the @test annotation.
There are three different annotations:
* @UnitTest
* @IntegrationTest
* @EndToEndTest

Then you can use the assert methods to check if the test is successful.
There is also a @Before and @After annotation that can be used to run code before and after each test.

### HTML report
Skyline generates a HTML report of the test results. This report can be found in the target/skyline folder.
The report contains the following information:
* The test name
* The test result
* The test duration

