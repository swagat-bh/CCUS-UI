package com.bh.at.step;
import com.bh.at.page_actions.CommonUIAction;
import com.bh.at.page_actions.LoginActions;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static com.bh.at.tester.BaseTester.actions;

public class LoginStep {
    private static Scenario scenario;
    @Before
    public void beforeStep(Scenario lScenario) {
        scenario = lScenario;
    }
    static {
        if (Objects.isNull(actions.getCommonUIActions())) {
            actions.setCommonUIActions(new CommonUIAction());
        }
    }
    static{
        if(Objects.isNull(actions.getLogin())) {
            actions.setLogin(new LoginActions());
        }
    }
        @Given("user navigates to CCUS application")
        public void launchApplication() {

            //actions.launchApplication();
            actions.getCommonUIActions().launchApplication();
        }
       @When("user enters username and password")
       public void userEntersUsernameAndPassword() {

            actions.userlogin();
    }
    @Then("user should be logged in successfully and lands on default Homepage")
    public void userShouldBeLoggedInSuccesfully() {

           actions.HomePage();
    }
    @And("user logsout succesfully")
    public void userLogsoutSuccesfully() {


        actions.logout();
    }

    @Then("User attempt to login should be blocked due to invalid credentials")
    public void userLoginShouldBeBlockedDueToInvalidCredentials() {

        actions.validateLogin();
    }
}
