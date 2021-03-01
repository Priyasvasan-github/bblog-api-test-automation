Feature: Verify user can follow and un-follow a profile

  @smoke
  Scenario: Verify user can follow an existing profile
    Given A registered user have a token to access API
    And the user is not following user with username followMeToTest
    When the user decides to follow user with username followMeToTest
    Then the user is following user with username followMeToTest

  @smoke
  Scenario: Verify user can un-follow an existing profile
    Given A registered user have a token to access API
    And the user is following user with username followMeToTest
    When the user decides to un-follow user with username followMeToTest
    Then the user is not following user with username followMeToTest

  @wip
  Scenario: Verify user can't follow his own profile
    Given A registered user have a token to access API
    When He attempts to follow his own profile
    Then He should be returned with an error

#end