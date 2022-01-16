@Functional_tests
Feature: Verify ToDo Page
  In order to remember the things
  I want to do, as a ToDo MVC user,
  I want to manage my todo list

  Background:
    Given User visit ToDo mvc Page

  Scenario Outline: Functional Test 01 - Newly Added Task appears in All and Active Tab

    When User adds "<Task>" in ToDo Box
    Then added Task "<Task>" appears in "All" Tab
    And added Task "<Task>" appears in "Active" Tab
    And User able to see total Items left as "1"
    And User Clears Added Task "<Task>"
    Then ToDo List is Empty
    Examples:
    |     Task                      |
    |   Learn Vue JS Basics         |


  Scenario Outline: Functional Test 02 - When Added Task Marked completed moves to Completed Tab
    When User adds "<Task>" in ToDo Box
    And added Task "<Task>" appears in "Active" Tab
    And User able to see total Items left as "1"
    When User marks added task "<Task>" as complete
    And User able to see total Items left as "0"
    And Switch to "Completed" Tab
    Then added Task "<Task>" appears in "Completed" Tab
    And User Clears Added Task "<Task>"
    Then ToDo List is Empty
    Examples:
      |     Task                    |
      |Boot strap Vue JS application|

  Scenario Outline: Functional Test 03 - Toggling Task between Complete and Incomplete appears appropriatly on  required Tab
    When User adds "<Task>" in ToDo Box
    And added Task "<Task>" appears in "Active" Tab
    And User able to see total Items left as "1"
    When User marks added task "<Task>" as complete
    And User able to see total Items left as "0"
    And Switch to "Completed" Tab
    Then added Task "<Task>" appears in "Completed" Tab
    When User marks added task "<Task>" as incomplete
    And User able to see total Items left as "1"
    And Switch to "Active" Tab
    And added Task "<Task>" appears in "Active" Tab
    And User Clears Added Task "<Task>"
    Then ToDo List is Empty
    Examples:
      |     Task                    |
      |Boot strap Vue JS application|


  Scenario Outline: Functional Test 04 - With more than a task added , Completed tasks are updated appropriately
    When User adds "<Task1>" in ToDo Box
    When User adds "<Task2>" in ToDo Box
    And User able to see total Items left as "2"
    And added Task "<Task1>" appears in "Active" Tab
    And added Task "<Task2>" appears in "Active" Tab
    When User marks added task "<Task1>" as complete
    And Switch to "Completed" Tab
    Then added Task "<Task1>" appears in "Completed" Tab
    And User Clears Added Task "<Task1>"
    And Switch to "Active" Tab
    And User able to see total Items left as "1"
    And User Clears Added Task "<Task2>"
    Then ToDo List is Empty
    Examples:
      | Task1                    |Task2                   |
      | Code Vue Js Application  | Test Vue JS Application |

  Scenario Outline: Functional Test 05 - With more than a task added , User is able to Toggles between Complete/Incomplete status
    When User adds "<Task1>" in ToDo Box
    When User adds "<Task2>" in ToDo Box
    And User able to see total Items left as "2"
    And added Task "<Task1>" appears in "Active" Tab
    And added Task "<Task2>" appears in "Active" Tab
    When User does Select All task in the list to mark complete
    And Switch to "Completed" Tab
    Then added Task "<Task1>" appears in "Completed" Tab
    Then added Task "<Task2>" appears in "Completed" Tab
    When User does Select All task in the list to mark Incomplete
    And Switch to "Active" Tab
    Then added Task "<Task1>" appears in "Active" Tab
    Then added Task "<Task2>" appears in "Active" Tab
    When User does Select All task in the list to mark complete
    Then User is able to Clear all completed jobs
    Then ToDo List is Empty

    Examples:
      | Task1                    |Task2                   |
      | Code Vue Js Application  | Test Vue JS Application |

  Scenario Outline: Functional Test 06 - With more than a task added ,User is able to Select all tasks to mark complete
    When User adds "<Task1>" in ToDo Box
    When User adds "<Task2>" in ToDo Box
    And added Task "<Task1>" appears in "Active" Tab
    And added Task "<Task2>" appears in "Active" Tab
    When User does Select All task in the list to mark complete
    And Switch to "Completed" Tab
    Then added Task "<Task1>" appears in "Completed" Tab
    Then added Task "<Task2>" appears in "Completed" Tab
    And User Clears Added Task "<Task1>"
    And User Clears Added Task "<Task2>"
    Then ToDo List is Empty
    Examples:
      | Task1 |Task2|
      | Code  | Test |


  Scenario Outline: Functional Test 07 - User is able to clear all Completed tasks
    When User adds "<Task1>" in ToDo Box
    When User adds "<Task2>" in ToDo Box
    When User does Select All task in the list to mark complete
    Then User is able to Clear all completed jobs
    Then ToDo List is Empty
    Examples:
      | Task1 |Task2|
      | Code  | Test |

  Scenario: Functional Test 08 - On Refresh of Page , Added To Do List remains same
    When User adds "Code" in ToDo Box
    Then User Refresh Page
    And added Task "Code" appears in "Active" Tab
    When User does Select All task in the list to mark complete
    Then User is able to Clear all completed jobs
    Then ToDo List is Empty

  Scenario: Functional Test 09 - With tasks to the list when user opens New Tab shows all added tasks
    When User adds "Code" in ToDo Box
    And added Task "Code" appears in "Active" Tab
    When User opens new Tab
    And added Task "Code" appears in "Active" Tab
    And closes new Tab
    When User does Select All task in the list to mark complete
    Then User is able to Clear all completed jobs
    Then ToDo List is Empty

  Scenario Outline: Functional Test 10 - User is able to add task including special characters
    When User adds "<Task>" in ToDo Box
    And added Task "<Task>" appears in "Active" Tab
    When User does Select All task in the list to mark complete
    Then User is able to Clear all completed jobs
    Then ToDo List is Empty
    Examples:
    |Task|
    |!@#$%^&*()_-+={[]};:?/,.`~|


  Scenario Outline: Functional Test 11 - User is able to add task with alpha numeric characters
    When User adds "<Task>" in ToDo Box
    And added Task "<Task>" appears in "Active" Tab
    When User does Select All task in the list to mark complete
    Then User is able to Clear all completed jobs
    Then ToDo List is Empty
    Examples:
      |Task|
      |Need to start coding on 01-02-2022 5:00 PM|

  Scenario Outline: Functional Test 11 - User is able to add task with decimal numbers
    When User adds "<Task>" in ToDo Box
    And added Task "<Task>" appears in "Active" Tab
    When User does Select All task in the list to mark complete
    Then User is able to Clear all completed jobs
    Then ToDo List is Empty
    Examples:
      |Task|
      |I need to pay person x $100.50 at 6 PM today|

  Scenario Outline: Functional Test 11 - User is able to add task with formatted numbers
    When User adds "<Task>" in ToDo Box
    And added Task "<Task>" appears in "Active" Tab
    When User does Select All task in the list to mark complete
    Then User is able to Clear all completed jobs
    Then ToDo List is Empty
    Examples:
      |Task|
      |I need to pay person x $100,000 at 12-12-22 6PM today|

  Scenario Outline: Functional Test 11 - User is able to copy paste to add task
    When User adds and copies "<Task>" in ToDo Box
    And added Task "<Task>" appears in "Active" Tab
    When User does Select All task in the list to mark complete
    Then User is able to Clear all completed jobs
    Then ToDo List is Empty
    Examples:
      |Task|
      |Copy Paste Task|


