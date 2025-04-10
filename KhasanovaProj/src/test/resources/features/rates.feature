Feature: Exchange rates feature

  Scenario Outline: R005 Check Exchange rates on UI
    Given I get '<exchangeRates>' from Privat bank
    And I open main page of Privat bank
    When I click on button Exchange rates on Header Element
    And I get '<exchangeRates>' from UI
    Then Check that exchange rates are the same on UI as on API

    Examples:
      | exchangeRates |
      | USD           |
      | EUR           |