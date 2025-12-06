Feature: login validation for HRM portal
  Background:
  Given User is on HRM login page
  @Login
  Scenario: Username field is empty
    When User leaves userName filed empty
    And User enters a valid password
    And User clicks on the Login button
    Then System displays "Username cannot be empty" near the userName field

    @Login
    Scenario: Password is empty
      When User enters a valid userName
      And User leaves a password empty
      And User clicks on the Login button
      Then System displays "Password is empty" near the password field

      @Login
      Scenario: User enters an invalid userName
        When User enters "Ana" in the userName field
        And User enters a valid password
        And User clicks on the Login button
        Then System displays "Invalid credentials" as an error message

  @Login
    #here I wanted to pass data through scenario outline, but couldn't figure out ho to pass correct credentials fro properties file
  Scenario: User enters an invalid password
    When User enters a valid userName
    And User enters "Ani" in the password field
    And User clicks on the Login button
    Then System displays "Invalid credentials" as an error message

    @Login
    Scenario: successful login after correcting invalid credentials
      When User enters "Ana" in the userName field
      And User enters "Ani" in the password field
      And User clicks on the Login button
      Then System displays "Invalid credentials" as an error message
      When User corrects the userName field with a correct userName
      And User corrects the password field with a correct password
      And User clicks on the Login button
      Then User is logged in successfully




        




