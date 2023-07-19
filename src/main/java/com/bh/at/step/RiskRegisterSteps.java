package com.bh.at.step;

import com.bh.at.page_actions.CommonUIAction;
import com.bh.at.page_actions.RiskRegisterUIActions;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static com.bh.at.tester.BaseTester.actions;

public class RiskRegisterSteps {

    private static Scenario scenario;

    @Before
    public void beforeStep(Scenario lScenario) {
        scenario = lScenario;
    }

    static {
        if (Objects.isNull(actions.getRiskRegister())) {
            actions.setRiskRegister(new RiskRegisterUIActions());
        }
    }


    @Given("verify Add New Risk button is visible under Risk Management page")
    public void verifyAddNewRiskButtonIsVisibleUnderRiskManagementPage() {

        actions.verifyAddNewRiskButton();
    }

    @When("user click on Add new risk tab")
    public void userClickOnAddNewRiskTab() {
        actions.clickOnAddNewRiskButtonN();
    }


    @Then("validate that create New Risk page open successfully")
    public void validateThatCreateNewRiskPageOpenSuccessfully() {
        actions.verifyCreateRiskPageAndRiskDetailsDropdownTitles();

    }

    @And("User is able to add Risk Details as per configuration")
    public void userIsAbleToAddRiskDetailsAsPerConfiguration() {
        actions.selectRiskDetailsDropDownValues();
    }

    @Then("user is able to create new risk")
    public void userIsAbleToCreateNewRisk() {
        actions.selectRiskSeverity();
        actions.addCommentsForRisk();
        actions.validateCancelButton();
        actions.verifyAndSelectCreateRiskButton();
    }

    @And("user Logout of the application")
    public void userLogoutOfTheApplication() {
    }

    @And("User is not able to add Risk Details without required fields")
    public void userIsNotAbleToAddRiskDetailsWithoutRequiredFields() {
        actions.selectCreateRiskButtonWithoutRequiredFields();
    }

    @Then("validate that error message appears to fill all required fields")
    public void validateThatErrorMessageAppearsToFillAllRequiredFields() {
        actions.verifyRequiredFieldMessage();
    }

    @Given("Verify that user is landed on Risk management page after login")
    public void verifyThatUserIsLandedOnRiskManagementPageAfterLogin() {
        actions.verifyAddNewRiskButton();
    }

    @When("User is able to click on meatball menu next to risk define")
    public void userIsAbleToClickOnMeatballMenuNextToRiskDefine() {
        actions.verifyAndSelectTheMeatballMenu();
    }

    @Then("Verify that the new menu with update and delete option pops up")
    public void verifyThatTheNewMenuWithUpdateAndDeleteOptionPopsUp() {
        actions.verifyDeleteAndUpdateOptionUnderMeatBallMenu();

    }

    @And("User is able to select the {string} option to delete {string} risks")
    public void userIsAbleToSelectTheOptionToDelete(String MenuOption, String NoOfItems) {
        actions.selectDeleteOption(MenuOption, NoOfItems);
    }
}
