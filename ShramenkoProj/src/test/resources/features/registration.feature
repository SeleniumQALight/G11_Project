Feature: Registration Feature

  Scenario Outline: R004 Invalid Registration
    Given I open Login page
    When I enter '<username>' into input Username in Login page
    And I enter '<email>' into input Email in Login page
    And I enter '<password>' into input CreatePassword in Login page
    Then I see error message with text '<errorMessages>'

    Examples:
      | username     | email        | password     | errorMessages                                                                                                            |  |  |
      | sh           | sh           | sh           | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |  |  |
      | invalidlogin | sh           | sh           | You must provide a valid email address.;Password must be at least 12 characters.                                         |  |  |
      | invalidlogin | in@gmail.com | tr           | Password must be at least 12 characters.                                                                                 |  |  |
      | qaauto       | in@gmail.com | 123456qwerty | This username is already taken.                                                                                          |  |  |
      | qaauto       |              | 123456qwerty | This username is already taken.                                                                                          |  |  |