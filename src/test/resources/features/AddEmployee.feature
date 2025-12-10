Feature: Add Employee to HRMS Application
  Background:
    Given User is logged and navigated to dashboardPage
    When User clicks on PIM option
    And User clicks on add employee option

  @AddEmployee @Smoke
  Scenario: adding an employee without providing ID
    When User enters first name "Ana" and middle name "" and last name " Makatsraia"
    And User clicks on the save button
    Then employee personal details is displayed
    And generated employee ID is not empty
    And user deletes the created employee

  @AddEmployee @Smoke @AddEmpId
  Scenario Outline: adding the employee by providing ID
    When User enters "<firstName>" "<lastName>" "<ID>" field values
    And User clicks on the save button
    Then employee personal details is displayed
    And generated employee ID is not empty
    And user deletes the created employee
    Examples:
        |firstName| lastName |ID|
        |Freddie  |Mercury  |0932|
        |Mia      |Mercy    |0735|
        |Anna     |Demon    |2345|

  @AddEmployee @Smoke
    Scenario Outline: validate error messages for invalid or incomplete employee data
      When User enters first name "<firstName>" and last name "<lastName>"
      And User clicks on the save button
      Then System should display "<expectedErrorMessage>" near the respective input fields "<inputField>"
      Examples:
      |firstName|lastName|expectedErrorMessage|inputField|
      |Ana      |        |Required            |lastName  |
      |         |Demons  |Required            |firstName |


