/****************************************************************************************************************
 * BH Confidential                                                                                              *
 * Unpublished Copyright 2023.  Baker Hughes Company                                                            *
 *                                                                                                              *
 * NOTICE:  All information contained herein is, and remains the property of  Baker Hughes Company,  and/or its *
 * affiliates. The intellectual and technical concepts contained herein are proprietary to Baker Hughes Company *
 * and/or its affiliates and may be covered by  patents,  copyrights,  and/or trade secrets.   Dissemination of *
 * this information or reproduction of this material is  strictly forbidden unless prior  written permission is *
 * obtained from Baker Hughes Company.                                                                          *
 ***************************************************************************************************************/

/* ** ** ** ** ** * AUTOGENERATED - DO NOT MODIFY, ANY MODIFICATIONS WILL BE OVERWRITTEN!!!!! * ** ** ** ** ** */

package com.bh.at.step;

import com.bh.at.tester.AlertRuleApiTester;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlertRuleApiSteps {
  private static final AlertRuleApiTester ALERT___RULE_API_TESTER = new AlertRuleApiTester();
  private static Scenario scenario;
  public AlertRuleApiSteps() {
    AlertRuleApiTester.testRunnerLogger = AlertRuleApiSteps::cukeLogger;
  }
  @Before
  public void beforeStep(Scenario lScenario) {
    scenario = lScenario;
  }
  @Given("the alert api file {string}")
  public void initAlert_rule_apiApi(String apiFile) {
    apiFile = apiFile.split(":")[0] + ".api";
    ALERT___RULE_API_TESTER.initAlert_rule_apiApi(apiFile);
  }
  @When("the user calls {string} in alert API")
  public void callPostAlert_rule_apiApi(String apiCall) {
    apiCall = apiCall.substring(0, apiCall.lastIndexOf(":"));
    ALERT___RULE_API_TESTER.callPostAlert_rule_apiApi(apiCall);
  }
  @Then("alert should be created with response status code {int} and alert Id")
  public void alert_rule_api_f1(Integer p0) {
    ALERT___RULE_API_TESTER.alert_rule_api_f1(p0);
  }
  @Then("alert api response should contain status code {int} with required description for tenant id")
  public void alert_rule_api_f2(Integer p0) {
    ALERT___RULE_API_TESTER.alert_rule_api_f2(p0);
  }
  @Then("alert api response should contain status code {int} with required description for required fields")
  public void alert_rule_api_f3(Integer p0) {
    ALERT___RULE_API_TESTER.alert_rule_api_f3(p0);
  }
  private static void cukeLogger(String msg) {
    System.out.println(msg);
    scenario.log(msg);
  }
}
