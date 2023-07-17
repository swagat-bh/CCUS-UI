#User Navigation to Asset Hierarchy

Feature: CCUS Asset Hierarchy UI Validations

  Background: Tenant is logged in to application
    Given user navigates to CCUS application
    When  user enters username and password
    Then  user should be logged in succesfull and lands on default Homepage

  Scenario: Verify the asset hieracrhy UI user Navigation and search functionality
    Given Asset Hierarchy View available in CCUS App
    When User navigates to Asset hierarchy view using Navigation drawer
    Then user should be able to search the asset entities

