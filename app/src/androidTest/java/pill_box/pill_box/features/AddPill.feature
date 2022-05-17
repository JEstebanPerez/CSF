Feature: AddPillFeature

    Scenario: Add a new pill
        Given Nothing
        When I enter the necessary fields
        And I click add button
        Then I should see the main page