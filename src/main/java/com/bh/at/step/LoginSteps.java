package com.bh.at.step;


import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import com.bh.at.page_actions.LoginActions;
import io.cucumber.java.en.When;
import org.joda.time.DateTime;

import static com.bh.at.tester.BaseTester.actions;

import java.util.Objects;


public class LoginSteps {

  private static Scenario scenario;

  @Before
  public void beforeStep(Scenario lScenario) {
    scenario = lScenario;
  }

  static{
    if(Objects.isNull(actions.getLogin())) {
      actions.setLogin(new LoginActions());
    }
  }


  @When("User login into the application")
  public void userLoginIntoTheApplication() {
    actions.login();
   /* DateTime dt = new DateTime();
    int hours = dt.getHourOfDay();
    if(hours%2==0)
      actions.switchTheme("Dark");*/
  }

  @When("admin login into the application")
  public void adminLoginIntoTheApplication() {
    actions.adminLogin();
  }

  @And("user logout from application")
  public void userLogoutFromApplication() {
    actions.logOut();
  }

  @When("User clicks on search button")
  public void userClicksOnSearchButton() {
    //actions.search();
  }
}
