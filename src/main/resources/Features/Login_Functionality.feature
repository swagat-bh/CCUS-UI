Feature: Tenant Login Functionality

   @Tes
   Scenario:User is able to login to CCUS App with valid credentials
      Given user navigates to CCUS application
      When  user enters username and password
      Then  user should be logged in succesfull and lands on default Homepage
      And   user logsout succesfully

  @Test
  Scenario:Verify that user is unable to login CCUS App with invalid credentials
       Given user navigates to CCUS application
       Then  User attempt to login should be blocked due to invalid credentials
