#Feature to verify Create alert flow
@TestReports
  Feature: Verify CCUS UI Create alert rule flow

    Background: Tenant is logged in to application
      Given user navigates to CCUS application
      When  user enters username and password
      Then  user should be logged in succesfull and lands on default Homepage

    Scenario: Verify that user is able to create alert from the UI
        Given user navigates to alertmanagement tab and clicks on Add alert button
        When user lands on create alert UI and enters all the required fields
        Then user clicks on Create alert button and ensure that alert is created succesfully

