Feature: Exchange Rate Feature

  Scenario Outline: P001 Verify currency rates consistency between API and UI

    Given I get exchange rate via API for '<currency>'
    When I get exchange rate via UI for '<currency>'
    Then I compare the API and UI exchange rates for '<currency>'

    Examples:
      | currency |
      | EUR      |
      | USD      |