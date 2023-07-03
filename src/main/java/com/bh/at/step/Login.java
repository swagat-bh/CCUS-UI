package com.bh.at.step;
import com.bh.at.page_actions.CommonUIAction;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static com.bh.at.tester.BaseTester.actions;

public class Login {

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

        @Given("user navigates to CCUS application")
        public void launchApplication() {

            //actions.launchApplication();

            actions.getCommonUIActions().launchApplication();

        }

       @When("user enters username and password")
       public void userEntersUsernameAndPassword() {

            actions.userlogin();

    }
}
