Feature: Registration errors messages feature



  Scenario Outline: R004 Check error messages on Registration page
    Given I open Login page page
    When  I enter '<username>' into input Username in Login page
    And I enter '<email>' into input Email in Login page
    And I enter '<password>' into input Password in Login page
    Then I see alert message with text '<errorMessage>' in Registration page

    Examples:
      | username | email            | password                                            | errorMessage                                                                                                             |
      | xo       | xo               | xo                                                  | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | tanita   | tanita@gmail.com | 123456789012345678901234567890123456789012345678901 | This username is already taken.;This email is already being used.;Password cannot exceed 50 characters.                  |