

  Feature:

    Scenario: R001 Valid Login
      Given I open Login page
      When I loggin with valid cred
      Then I see avatar on page