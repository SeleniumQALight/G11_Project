Feature: Error message on sign up form

  Scenario Outline: R004 Check error message on sign up form
    Given I open Login page
    When I enter '<userName>' on sign up form input Username in Login page
    And I enter '<email>' on sign up form input Email in Login page
    And I enter '<password>' on sign up form input Password in Login page
    And I click on button SignUp in Login page
    Then I see alert message with '<errorMessage>' text

    Examples:
      | userName | email           | password             | errorMessage                                                                                                     |
      | qaauto   | not_valid_email | nv                   | This username is already taken.;You must provide a valid email address.;Password must be at least 12 characters. |
      | nv       | veivua@gmail    | 1234qwerty1234qwerty | Username must be at least 3 characters.                                                                          |