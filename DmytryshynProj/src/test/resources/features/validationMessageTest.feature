Feature: Validation messages test feature


  @R004
  Scenario Outline: R004 Check Validation Messages
    Given I open Login page
    When I enter '<username>' in Username field on Login page
    And I enter '<email>' in Email field on Login page
    And I enter '<password>' in Password field on Login page
    Then I see '<expectedMessages>' on Login page

    Examples:
      | username | email | password | expectedMessages                                                                                                         |
      | tr       | tr    | tr       | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | ttrr     | tr    | tr       | You must provide a valid email address.;Password must be at least 12 characters.                                         |