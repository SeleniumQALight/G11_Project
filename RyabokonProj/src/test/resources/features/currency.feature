Feature: Private Bank Currency

  Scenario Outline: R005 Compare exchange rates from API and UI for <currency>
    Given I get the exchange rate for "<currency>" from PrivatBank API
    When I open PrivatBank HomePage
    Then I get the exchange rate for "<currency>" from the UI
    And I compare API exchange rate and UI exchange rate for "<currency>"

    Examples:
      | currency |
      | USD      |
      | EUR      |