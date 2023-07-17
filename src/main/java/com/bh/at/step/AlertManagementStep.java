package com.bh.at.step;

import com.bh.at.page_actions.AlertmanagementUIActions;
import com.bh.at.page_actions.CommonUIAction;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static com.bh.at.tester.BaseTester.actions;

public class AlertManagementStep {

    private static Scenario scenario;

    @Before
    public void beforeStep(Scenario lScenario) {
        scenario = lScenario;
    }

    static {
        if (Objects.isNull(actions.getAlertmngmntActions())) {
            actions.setAlertmngmntActions(new AlertmanagementUIActions());
        }
    }


    @Given("user navigates to alertmanagement tab and clicks on Add alert button")
    public void navigateToAlertmngmntTab()
    {
      actions.navigateToAlertmngmntTab();

    }


    @When("user lands on create alert UI and enters all the required fields")
    public void navigateTocreatealertui() {

    actions.navigateTocreatealertui();

    }


    @Then("user clicks on Create alert button and ensure that alert is created succesfully")
    public void createAlert() {
     actions.createAlert();

    }
}
