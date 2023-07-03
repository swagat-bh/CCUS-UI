package com.bh.at.step;

import com.bh.at.page_actions.CommonUIAction;
import com.bh.at.page_actions.CommonUIActions;
import com.bh.at.utils.CsvUtil;
import com.bh.at.utils.MQUtil;
import com.bh.commonutil.CyBaseException;
import com.bh.icommonallutil.IBaseException;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.util.List;
import java.util.Objects;

import static com.bh.at.tester.BaseTester.actions;

public class CommonUISteps {

  private static Scenario scenario;

  static {
    if (Objects.isNull(actions.getCommonUIActions())) {
      actions.setCommonUIActions(new CommonUIAction());
    }
  }

  @Before
  public void beforeStep(Scenario lScenario) {
    scenario = lScenario;
  }

  @Given("User launches the application")
  public void userLaunchesTheApplication() {
    actions.launchBrowser();
  }

  @Then("User closes the application")
  public void userClosesTheApplication() {
    actions.quitBrowser();

  }
  @Given("Validate step {string}")
  public void validate_step(String str) {
    Assert.assertEquals("true",str);
  }

  @Given("User launches the Augury application")
  public void userLaunchesTheAuguryApplication() {
    actions.auguryLaunchApplication();
  }



  @When("User perform {string} asset mapping from different source systems")
  public void userPerformAssetMappingFromDifferentSourceSystems(String project) throws IBaseException {
    actions.performAssetMappingFromDifferentSourceSystems(project);
  }

  @Then("User perform asset unmapping of {string} from different source systems")
  public void userPerformAssetUnmappingOfFromDifferentSourceSystems(String project) throws IBaseException {
    actions.AssetUnmappingFromDifferentSourceSystems(project);
  }

  @Given("Prepare list of enterprise ids to delete from file {string}")
  public void prepareListOfEnterpriseIdsToDelete(String CSV_FILE_PATH) throws CyBaseException, IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
    CsvUtil obj = new CsvUtil();
    List<String> values = obj.getGroupIdFromCSV(new File(FileSystems.getDefault().getPath(".").toAbsolutePath()+"/src/main/resources/TestData/"+CSV_FILE_PATH));
    MQUtil.enterpriseIdsStack.clear();
    for (int i = 1; i < values.size(); i++) {
      MQUtil.enterpriseIdsStack.add(values.get(i));
    }
  }
}
