# QA-Assignment
Repository created for QA Assessment 

AUT - https://todomvc.com/examples/vue/




# Tech Specs used

Programming language - Java (V -14.0.2 2020-07-14)

Build tool - Maven (V- 3.6.3)

BDD Framework - Cucumber (V - 5.7.0)

Test Framework - J unit (V- 4.13.2)

Web driver - Selenium (V- 3.141.59)

Machine used for script development - Mac 11.6.1 (20G224)

Maven Compiler source/target Java version - 1.8

Spring concepts used for Annotations (V- 5.2.6.RELEASE)




# Test Automation Framework Design

- Maven project 

***************src/main/java has ********************

1. Project Package in turn having 
  
  1.1 Pages -> Locator Methods (User Interactions)
  
  1.2 Steps -> Intermediate between Step definitions and Pages for Re usability 

2. Config Package 

  2.1 Browser Library -> Driver related methods (Annotated with Spring Component)
  
  2.2 Config Details -> Configuration Methods (Annotated with Spring Configuration)
  
  2.3 Property File Methods 
  
 3. Exceptions Package 
 
  3.1 CustomException -> Extension of Run time exceptions 
  
  3.2 CustomExceptionType -> ENUM of Custom Exception types handled
  
 4. svc
 
  4.1 MasterthoughtReportsSvc -> Report Generation Implementation
  
 5. utils 
  
   5.1 FileDirUtils -> reusable Methods for File / Folders (Annotated with Spring Component)
   
   5.2 LocatorActions -> reusable methods for user interaction Implementation (Annotated with Spring service)
   
   5.3 LoggerUtil -> reusable methods for Configuring Logs (Annotated with Spring Component)
   
   5.4 ScenarioUtils -> reusable methods for Cucumber scenarios handling (Annotated with Spring Component)
   
 
6. resources 
  
  6.1 locators ->  Property file where locators are defined page wise 
  
  6.2 log4j.xml -> definition of log generation 
  

****************src/test/java has ******************
  
 1. Project Package in turn having 
  
   1.1 stepdefs -> Step Definitons , Hooks and Cucumber Spring Configuration (to set context)
   
   1.2 suite -> Runner file for Cucumber
   
   1.3 resources -> feature files and test configurations (Example - AUT which is common across Framework)
   
 

# Pre Requisite for running the Framework 

******(Machine Used - Mac)*******

1. Java installed  (JDK 14)

2. Maven Installed (V - 3.6.3) 

3. git installed (V-2.23.0)

4. open Terminal and enter command - git clone https://github.com/aaditya5490/QA-Assignment.git

5. Folder with name "QA-Assignmen" will be created 

6. change directory to "QA-Assignmen"

7. verify file contents (pom.xml ,src) 

8. provide command based on execution type 

  8.1  Smoke tests -> mvn clean verify -Dbrowser=chrome -Denv=preprod -Dcucumber.filter.tags=@Smoke_tests
  
  8.2 Functional tests -> mvn clean verify -Dbrowser=chrome -Denv=preprod -Dcucumber.filter.tags=@Functional_tests

# Reports 
Once Execution is over navigate to target folder for viewing output of executon 

  1 Html Report -> Open target/cucumber-advanced-reports/cucumber-html-reports/overview-features.html created under Project Directory (if there a failure will be screenshot is taken placed in target/cucumber-advanced-reports/creenshots and embedded to html file mentioned earlier 
  
  2 Log -> Log will be created under target folder with Time Stamp of execution



