
Feature: PB Rate Match Feature

  Scenario Outline: PB0003 Comparison of rates using the UI and API
    Given I have the "<currency>" currency rate from API
    When I open the PrivatBank home page
    Then I have the "<currency>" currency rate from UI
    And I compare the "<currency>" currency rates from API and UI

    Examples:
        | currency |
        | USD      |
        | EUR      |