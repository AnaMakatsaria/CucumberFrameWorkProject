Feature: Search Employee in HRMS Application
  Background:
    Given User is logged and navigated to dashboardPage
    When User clicks on PIM option
    And User clicks on add employee option
    And User enters first name "Ana" and last name " Makatsaria"
    And User clicks on the save button
    Then employee personal details is displayed
    And the generated employee ID is saved
    And User clicks on employee list option



  Scenario Outline: Search employee using valid search criteria
    When the user searches for an employee using "<searchType>" with value "<searchValue>"
    Then the system should display employee records matching "<searchType>"
    And the displayed employee records should contain "<searchValue>"

    Examples:
      | searchType   | searchValue        |
      | Employee ID  | dynamic_employee_id|
      | Full Name    | Ana Makatsaria     |
      | Partial Name | Ana                |

  Scenario Outline: Search employee using invalid search criteria
    When the user searches for an employee using "<searchType>" with value "<searchValue>"
    Then the system should display a "No Records Found" message

    Examples:
      | searchType   | searchValue       |
      | Employee ID  | 999999            |
      | Full Name    | Nonexistent Name  |
      | Partial Name | XYZABC            |