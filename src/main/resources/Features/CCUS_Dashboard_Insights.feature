
Feature: Dashboard Insights Functionalities

    Background: Tenant is logged in to application
        Given user navigates to CCUS application
        When  user enters username and password

    Scenario: Verify Dashboard Asset Insights UI
        Given User lands on Dashboard Insights
        And   User navigates to Asset Insights
        Then  User verifies Asset Insights Dashboard UI page
