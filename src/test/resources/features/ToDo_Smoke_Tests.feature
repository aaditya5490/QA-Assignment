@Smoke_tests
Feature: To Do Smoke Tests
  As a user , When I navighate to ToDo Home Page , I should be able to see Home Page components loaded and able to add/delete task on the list

  @Smoke_tests
  Scenario: Verify To Do Page Navigation
    Given User visit ToDo mvc Page
    Then User is able to see Components Loaded in the Page
    Then ToDo List is Empty
    When User enters the task "Smoke Test Task" in ToDo Box
    And User Clears Added Task "Smoke Test Task"
    Then ToDo List is Empty
