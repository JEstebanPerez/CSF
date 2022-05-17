Feature: AddAppointmentFeature

    Scenario: Add a new appointment
        Given Nothing
        When I enter the necessary fields
        And I click add button
        Then I should see the main page