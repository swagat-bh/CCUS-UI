package com.bh.at.step;

import com.bh.at.page_actions.AlertManagementUIActions;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static com.bh.at.tester.BaseTester.actions;

public class AlertManagementSteps {
    private static Scenario scenario;
    @Before
    public void beforeStep(Scenario lScenario) {
        scenario = lScenario;
    }
    static {
        if (Objects.isNull(actions.getAlertmngmntActions())) {
            actions.setAlertmngmntActions(new AlertManagementUIActions());
        }
    }
    @When("user lands on create alert UI and enters all the required fields")
    public void navigateTocreatealertui() {

    actions.navigateTocreatealertui();
    }
    @Then("user clicks on Create alert button and ensure that alert is created successfully")
    public void createAlert() {
     actions.createAlertButton();
    }
    @Given("user navigates to alert-management tab")
    public void userNavigatesToAlertManagementTab() {
        actions.navigateToAlertmngmntTab();
    }
    @When("user search for a alert with alert name keyword")
    public void userSearchForAAlertWithAlertNameKeyword() {
       actions.searchAlertinUI();
    }
    @Then("searched alert should be available in list")
    public void searchedAlertShouldBeAvailableInList() {
        actions.searchedAlertResult();
    }
    @And("user should be able to delete the searched alert")
    public void userShouldBeAbleToDeleteTheSearchedAlert() {
        actions.deleteAlert();
    }
    @And("clicks on Add alert button")
    public void clicksOnAddAlertButton() {
        actions.clickOnAddalertButton();
    }
}
