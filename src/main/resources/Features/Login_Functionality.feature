Feature: Tenant Login Functionality

   @Test
  Scenario:User is able to login to CCUS App with valid credentials
      Given user navigates to CCUS application
      When  user enters username and password
      Then  user should be logged in succesfull and lands on default Homepage
      And   user logsout succesfully

