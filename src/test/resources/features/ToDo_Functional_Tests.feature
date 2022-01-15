@Functional_tests
Feature: Verify ToDo Page
  In order to remember the things
  I want to do, as a ToDo MVC user,
  I want to manage my todo list


  Scenario Outline: Newly Added Task appears in Active Tab
    Given User visit ToDo mvc Page
    When User enters the task "<Task>" in ToDo Box
    Then Added Task "<Task>" appears in "All" Tab
    And Added Task "<Task>" appears in "Active" Tab
    And User able to see total Items left as "1"
    And User Clears Added Task "<Task>"
    Then ToDo List is Empty
    Examples:
    |     Task                      |
    |   Learn Vue JS Basics         |


  Scenario Outline: When Task Marked completed moves to Completed Tab
    Given User visit ToDo mvc Page
    When User enters the task "<Task>" in ToDo Box
    And Added Task "<Task>" appears in "Active" Tab
    And User able to see total Items left as "1"
    When User marks added task "<Task>" as complete
    And User able to see total Items left as "0"
    #Then Added Task "<Task>" appears in "All" Tab
    And Switch to "Completed" Tab
    Then Added Task "<Task>" appears in "Completed" Tab
    And User Clears Added Task "<Task>"
    Then ToDo List is Empty
    Examples:
      |     Task                    |
      |Boot strap Vue JS application|


  Scenario Outline: With more than a task added , Completed tasks are updated appropriately
    Given User visit ToDo mvc Page
    When User enters the task "<Task1>" in ToDo Box
    When User enters the task "<Task2>" in ToDo Box
    And User able to see total Items left as "2"
    And Added Task "<Task1>" appears in "Active" Tab
    And Added Task "<Task2>" appears in "Active" Tab
    When User marks added task "<Task1>" as complete
    #Then Added Task "<Task2>" appears in "Active" Tab
    And Switch to "Completed" Tab
    Then Added Task "<Task1>" appears in "Completed" Tab
    And User Clears Added Task "<Task1>"
    And Switch to "Active" Tab
    And User able to see total Items left as "1"
    And User Clears Added Task "<Task2>"
    Then ToDo List is Empty
    Examples:
      | Task1                    |Task2                   |
      | Code Vue Js Application  | Test Vue JS Application |

  Scenario Outline: With more than a task added , Select all tasks to mark complete
    Given User visit ToDo mvc Page
    When User enters the task "<Task1>" in ToDo Box
    When User enters the task "<Task2>" in ToDo Box
    And Added Task "<Task1>" appears in "Active" Tab
    And Added Task "<Task2>" appears in "Active" Tab
    When User does Select All task in the list to mark complete
    And Switch to "Completed" Tab
    Then Added Task "<Task1>" appears in "Completed" Tab
    Then Added Task "<Task2>" appears in "Completed" Tab
    And User Clears Added Task "<Task1>"
    And User Clears Added Task "<Task2>"
    Then ToDo List is Empty
    Examples:
      | Task1 |Task2|
      | Code  | Test |


  Scenario Outline: User is able to clear more than one completed jobs
    Given User visit ToDo mvc Page
    When User enters the task "<Task1>" in ToDo Box
    When User enters the task "<Task2>" in ToDo Box
    When User does Select All task in the list to mark complete
    Then User is able to Clear all completed jobs
    Then ToDo List is Empty
    Examples:
      | Task1 |Task2|
      | Code  | Test |

  Scenario: On Refresh of Page , To Do List remnains same
    Given User visit ToDo mvc Page
    When User enters the task "Code" in ToDo Box
    Then User Refresh Page
    And Added Task "Code" appears in "Active" Tab

  Scenario: New Tab opens with existing list
    Given User visit ToDo mvc Page
    When User enters the task "Code" in ToDo Box
    And Added Task "Code" appears in "Active" Tab
    When User opens new Tab
    And Added Task "Code" appears in "Active" Tab


