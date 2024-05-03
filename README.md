# Web Technical Assignment

This project is a Java Selenium Project

## Test Cases
* TestLogin Functionality - 2TC
* TestLogOut Functionality - 1TC
* TestMyInfo Functionality - 1TC

## Code Structure
src/main/java contains all the reusbale classes
* Abstract Components
* PageObjects
* resources

All the PageObjects Classes extends Abstract Components

src/test/java contains all the test classes
* BaseTest
* Tests

All the Test Classes extends BaseTest

## Tools

* Maven
* TestNG
* Selenium Webdriver

## Requirements

In order to utilise this project you need to have the following installed locally:

* Java 21 
* Maven 
* Chrome
* Firefox
* Edge

## Usage

To run all the testcases(default chrome browser), navigate to project directory and run:

`mvn test -PRegression`

To run all the testcases in Edge, navigate to project directory and run:

`mvn test -PRegression -Dbrowser=Edge`

To run all the testcases in Edge, navigate to project directory and run:

`mvn test -PRegression -Dbrowser=Firefox`

## Reporting

Report index.html is generated in the reports folder. 
Incase of test failures, Screenshots of failed testcases are also stored in the reports folder.
