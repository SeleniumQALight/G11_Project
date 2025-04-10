Feature: Login Feature

  @R006
  Scenario: R006 Compare currency between API and UI
    Given I get currency list from API
    When I open PrivatBank UI page
    And I get currency list from UI
    Then I compare currency list between API and UI