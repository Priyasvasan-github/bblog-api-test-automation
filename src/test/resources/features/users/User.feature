Feature: Verify all routes of user are working as expected

  @smoke
  Scenario: Verify user login creates a token and valid user is retrieved
    Given A registered user is able to access the API
    When the user login using his credentials
    Then the user login is successful
    And the registered user details are displayed correctly

  Scenario: Verify user login creates an error with invalid login
    Given A anonymous user is able to access the API
    When the user login using invalid credentials
    Then the user login is not successful

  @smoke
  Scenario: Verify existing user cant be registered with existing login details
    Given A registered user is able to access the API
    When the registered user attempts to register
    Then the user registration is not successful

  @smoke
  Scenario: Verify a user with valid details is able to register successfully
    Given A valid user is able to access the API
    When the valid user attempts to register
    Then the user registration is successful

#end