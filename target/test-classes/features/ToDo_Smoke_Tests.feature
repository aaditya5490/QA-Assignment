@Smoke_tests
Feature: To Do Functional Tests
  As a user , I should see follow us links on af site

  @Smoke_tests
  Scenario: Verify To Do Page Navigation
    Given User visit ToDo mvc Page
    Then User is able to see Components Loaded in the Page
    Then ToDo List is Empty
