Feature: Login Feature

  @R004
  Scenario Outline: R004 Registration messages
    Given I open login page
    When I enter <username> and <email> and <password>
    Then I see <error messages> messages

    Examples:
      | username | email | password | error messages                                                                                                           |
      | tr       | tr    | tr       | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | trrr     | tr    | tr       | You must provide a valid email address.;Password must be at least 12 characters.                                         |