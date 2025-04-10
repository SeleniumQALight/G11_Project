Feature: PrivatBank Currency Feature


  Scenario Outline: R008 Verify currency rates from API and UI
    Given I get currency rates from API for "<currency>"
    When I open PrivatBank main page
    Then I get currency rates from UI for "<currency>"
    And I compare API and UI currency rates for "<currency>"

    Examples:
      | currency |
      | USD      |
      | EUR      |