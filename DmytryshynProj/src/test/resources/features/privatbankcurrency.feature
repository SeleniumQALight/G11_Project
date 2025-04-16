Feature: Privat Bank Exchange Rates Feature

  @R005
  Scenario Outline: R005 Check Exchange Rates API and UI
    Given I get exchange rate via Privat Bank API for '<currency>'
    When I get exchange rate via Privat Bank UI for '<currency>'
    Then I compare exchange rates on API and UI for '<currency>'

    Examples:
      | currency |
      | USD      |
      | EUR      |