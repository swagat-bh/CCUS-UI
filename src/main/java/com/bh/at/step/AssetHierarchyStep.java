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
public class AssetHierarchyStep {
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
  @Given("Asset Hierarchy View available in CCUS App")
    public void validateAssetHierarchyview()
    {
        actions.verifyAssetHierarchyview();
    }
    @When("User navigates to Asset hierarchy view using Navigation drawer")
    public void userNavigatesToAssetHierarchyViewUsingNavigationDrawer() {
          actions.navigateToAssetHierarchyview();
    }

    @Then("user should be able to search the asset entities")
    public void userShouldBeAbleToSearchTheAssetEntities() {
        actions.serachInAssetHierarchyview();
    }
}
