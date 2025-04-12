Feature: Validation feature

  Scenario Outline: R004 Check validation messages on registration
    Given I open Login page
    When I enter '<userName>' on sign up form input Username in Login page
    And I enter '<email>' on sign up form input Email in Login page
    And I enter '<password>' on sign up form input Password in Login page
    Then I see alert message with '<errorMessage>' text


    Examples:
      | userName | email                | password     | errorMessage                                                                                                             |
      | tr       | tr                   | tr           | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | qaauto   | valid_email@mail.com | qwerty123456 | This username is already taken.                                                                                          |


