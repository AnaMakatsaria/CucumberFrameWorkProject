Feature: login validation for HRM portal
  Background:
    Given User is navigated into HRMS Website

    @Login
  Scenario Outline: validating error messages for empty field
    When User enters "<userName>" "<password>" as login credentials
    And User clicks on the Login button
    Then System displays "<errorMessage>" as an error message
    Examples:
      |userName|password|errorMessage|
      |        |Hrm_user@123|Username cannot be empty|
      |hrm_user|            |Password is empty       |
      |Ana     |Hrm_user@123|Invalid credentials     |
      |hrm_user|hrm-90      |Invalid credentials     |


    @Login
    Scenario: successful login after correcting invalid credentials
      When User attempts to log in with incorrect credentials correct error message is displayed
      |userName|password    |expectedErrorMessage|
      |Ana     |Hrm_user@123|Invalid credentials|
      |hrm_user|hr-8        |Invalid credentials|
      And User clicks on the Login button
      When User resets with correct credentials
      |userName|password|
      |hrm_user|Hrm_user@123|
      And User clicks on the Login button
      Then User is logged in successfully and can see the text "Dashboard"








        




