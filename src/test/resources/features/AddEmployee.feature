Feature: Add Employee to HRMS Application
  Background:
    Given User is logged and navigated to dashboardPage
    When User clicks on PIM option
    And User clicks on add employee option
    @AddEmployee
  Scenario: adding an employee without providing ID

    When User enters first name "Ana" and middle name "" and last name " Makatsraia"
    And User clicks on the save button
    Then employee personal details is displayed
    And generated employee ID is not empty
    And user deletes the created employee

      Scenario Outline: adding the employee by providing ID
        When User enters "<firstName>" "<lastName>" "<ID>" field values
        And User clicks on the save button
        Then employee personal details is displayed
        And generated employee ID is not empty
        And user deletes the created employee
        Examples:
        |firstName| lastName |ID|
        |Freddie  |Mercurry  |0932|
        |Mia      |Mercy     |0735|
        |Anna     |Demon     |2345|


