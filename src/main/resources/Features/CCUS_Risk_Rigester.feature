@CCUSApp @Risk_Register_E2E @US435778 @US436466 @US436468
Feature: E2E Risk management validate, Add, update and Delete new Risk under Risk Management

    Background: Tenant is logged in to application
        Given user navigates to CCUS application
        When  user enters username and password
        Then  user should be logged in succesfull and lands on default Homepage

    @Risk_Management_Add_new_Risk
    Scenario: After logging to CCUS application Add new Risk Under Risk Management
        Given verify Add New Risk button is visible under Risk Management page
        When user click on Add new risk tab
        Then validate that create New Risk page open successfully
        And User is able to add Risk Details as per configuration
        Then user is able to create new risk
#        And user Logout of the application

    @Risk_Management_Add_new_Risk_without_required_fields
    Scenario: After logging to CCUS application Add new Risk Under Risk Management without required fields
        Given verify Add New Risk button is visible under Risk Management page
        When user click on Add new risk tab
        Then validate that create New Risk page open successfully
        And User is not able to add Risk Details without required fields
        Then validate that error message appears to fill all required fields

    @Risk_Management_Delete_Existing_Risk
    Scenario Outline: Login to CCUS Application select the existing risk and delete the Risk
        Given Verify that user is landed on Risk management page after login
        When User is able to click on meatball menu next to risk define
        Then Verify that the new menu with update and delete option pops up
        And User is able to select the "<OPTIONS>" option to delete "<NO_OF_RISKS>" risks

        Examples:
          |TC_ID| NO_OF_RISKS | OPTIONS |
          |1    | 10           | Delete  |



