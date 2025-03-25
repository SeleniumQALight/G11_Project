

  Feature: Login


    Scenario: R001 Valid login
      Given I open Login page
      When I loggin with valid cred
      Then I see avatar on HomePage