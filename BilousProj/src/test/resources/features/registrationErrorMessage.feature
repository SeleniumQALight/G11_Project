Feature: Validation Messages Test

  Scenario Outline: R004 Validate registration error messages
    Given I open Login page
    When I input "<username>" into the username field
    And I input "<email>" into the email field
    And I input "<password>" into the password field
    Then I should see the error messages '<expectedErrorsMessages>'

    Examples:
      | username | email | password | expectedErrorsMessages                                                                                                   |
      | tr       | tr    | tr       | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | ttrr     | tr    | tr       | You must provide a valid email address.;Password must be at least 12 characters.                                         |