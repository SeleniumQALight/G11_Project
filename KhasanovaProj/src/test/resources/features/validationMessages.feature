Feature: Validation messages on Registration page feature

  Scenario Outline: R004 Check validation messages on Registration page
    Given I open Login page
    When I enter '<username>' in Username field on Login page
    And I enter '<email>' in Email field on Login page
    And I enter '<password>' in Password field on Login page
    Then I see '<expectedMessages>' on Login page

    Examples:
      | username | email | password | expectedMessages                                                                                                         |
      | tr       | tr    | tr       | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | test7    | tr    | tr       | You must provide a valid email address.;Password must be at least 12 characters.                                         |



