Feature: Currency Exchange Rate Comparison

  @R007
  Scenario Outline: Compare currency rates from API and UI
    Given I fetch the "<currency>" exchange rate from PrivatBank API
    When I open the PrivatBank main page
    Then I fetch the "<currency>" exchange rate from PrivatBank UI
    And I match the API with UI exchange rates for '<currency>'

    Examples:
      | currency |
      | EUR      |
      | USD      |