Feature: Add Employee to HRMS Application
  Background:
    Given User is logged and navigated to dashboardPage
    @AddEmployee

  Scenario: adding an employee without providing ID
    When User clicks on PIM option
    And User clicks on add employee option
    When User enters first name "Ana" and middle name "" and last name " Makatsraia"
    And User clicks on the save button
    Then employee personal details is displayed
    And generated employee ID is not empty
    And user deletes the created employee