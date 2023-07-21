#Feature to verify Create alert flow
@CCUS_APP_UI_TEST
  Feature: Verify CCUS UI Create alert rule flow

    Background: Tenant is logged in to application
        Given user navigates to CCUS application
        When  user enters username and password
        Then  user should be logged in successfully and lands on default Homepage

    Scenario: Verify that user is able to create alert from the UI
        Given user navigates to alert-management tab
        And clicks on Add alert button
        When user lands on create alert UI and enters all the required fields
        Then user clicks on Create alert button and ensure that alert is created successfully
@Test
    Scenario: Verify that user is able to delete alert from the UI
        Given user navigates to alert-management tab
        When user search for a alert with alert name keyword
        Then searched alert should be available in list
        And user should be able to delete the searched alert
        


        


