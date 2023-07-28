package com.bh.at.step;
import com.bh.at.tester.CommonApiTester;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonApiSteps {

    private static final CommonApiTester commonApiTester= new CommonApiTester();
    public CommonApiSteps() {
        CommonApiTester.testRunnerLogger = CommonApiSteps::cukeLogger;
    }

    private static Scenario scenario;
    @Before
    public void beforeStep(Scenario lScenario) {
        scenario = lScenario;
    }

    @Given("the token api file {string}")
    public void callTokenAPI(String apiFile)
    {
        apiFile = apiFile.split(":")[0] + ".api";
        commonApiTester.initTokenCall(apiFile);
    }

    @When("the user calls method {string}")
    public void theUserCallsMethod(String apiCall) {
        apiCall = apiCall.substring(0, apiCall.lastIndexOf(":"));
        commonApiTester.callTokenAPI(apiCall);
    }

    @Then("api response should return status code {int} successfully")
    public void validAPITokenShouldBeGenerated(Integer p0) {
        commonApiTester.validateToken();
    }

    private static void cukeLogger(String msg) {
        System.out.println(msg);
        scenario.log(msg);
    }


}
