package com.bh.at.step;
import com.bh.at.page_actions.CommonUIAction;
import com.bh.at.page_actions.DashboardActions;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static com.bh.at.tester.BaseTester.actions;
public class DashBoardUiSteps {
    private static Scenario scenario;
    @Before
    public void beforeStep(Scenario lScenario) {
        scenario = lScenario;
    }


    static {
        if (Objects.isNull(actions.getDashboardActions())) {
            actions.setDashboardActions(new DashboardActions());
        }
    }

    @Given("User lands on Dashboard Insights")
    public void dashBoardInsights()
    {
        actions.DashboardInsights();
    }


    @And("User navigates to Asset Insights")
    public void userNavigatesToAssetInsights() {

       actions.navigateToAssetInsights();
        
    }

    @Then("User verifies Asset Insights Dashboard UI page")
    public void userVerifiesAssetInsightsDashboardUIPage() {

        actions.AssetInsights();
    }
}
