Feature: PrivatCurrency

  Scenario Outline: R006 Compare currency between API and UI
    Given I get '<currency>' from API
    When I open PrivatBank UI page
    And I get '<currency>' from UI
    Then I compare currency between API and UI

    Examples:
      | currency |
      | USD      |
      | EUR      |