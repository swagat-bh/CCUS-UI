package com.bh.at.page_actions;
import com.bh.at.iuiutil.IElement;
import com.bh.at.iuiutil.IFrame;
import com.bh.at.page_actions.iActions.IAlertmanagementUI;
import com.bh.at.uiutil.CyElements;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;
import static com.bh.at.core.DriverManager.ElementType.*;
import static com.bh.at.page_actions.CommonUIAction.appPage;
import static com.bh.at.tester.BaseTester.uiAction;

public class AlertManagementUIActions implements IAlertmanagementUI {
    private static final Logger LOG = LoggerFactory.getLogger(LoginActions.class);
    private final String PAGE = "alertmanagement";
    private final String ASSETPAGE = "assetHierarchy";
    public static final int defaultTimeout = 5000;
    @Override
    public void navigateToAlertmngmntTab() {

        appPage.pause(defaultTimeout);
        try {
            ((IFrame) uiAction.getElement(FRAME, PAGE, "IFRAME_WRAPPER")).setAsCurrent();
            uiAction.getElement(DIV, PAGE, "ALERT_HOMEPAGE").isDisplayed();
            uiAction.getElement(DIV, PAGE, "ALERT_HOMEPAGE").jsClick();
            appPage.pause(2000);
        } catch (Exception e) {
            Assert.fail("Alertmanagement UI is not available ");
        }
    }
    @Override
    public void navigateTocreatealertui() {
        LOG.info("Adding required  rule conditions");
        ((IFrame) uiAction.getElement(FRAME, PAGE, "IFRAME_WRAPPER")).setAsCurrent();
        int Numberofalerts = 0;
        while (Numberofalerts != 0) {
            uiAction.getElements(BUTTON, PAGE, "CREATE_ALERTRULE_ADD_DELETE_BUTTON").get(0).click();
            appPage.pause(500);
            Numberofalerts--;
        }
        LOG.info("Filling Alert rule required fields ");
        uiAction.getElement(INPUT, PAGE, "CREATE_ALERTUI_ALERTNAME").sendKeys("CyborgAuto" + UUID.randomUUID().toString().substring(0, 4));
        uiAction.getElement(INPUT, PAGE, "CREATE_ALERTUI_DESCRIPTION").sendKeys("CyborgAutomation");
        uiAction.getElements(BUTTON, PAGE, "CREATE_ALERT_SEVERITY").get(0).click();
        uiAction.getElement(BUTTON, PAGE, "CREATE_ALERTUI_SELECTASSET").click();
        appPage.pause(2000);
        CyElements<IElement> assets = uiAction.getElements(BUTTON, ASSETPAGE, "ASSET_EXPANDED_NODE");
        for (IElement ele : assets) {

            if (ele.isDisplayed()) {
                ele.jsClick();
                uiAction.moveTo(1, 1);
                uiAction.click();
                break;
            }
        }
        CyElements<IElement> sensor = uiAction.getElements(DIV, PAGE, "CREATE_ALERTUI_PARAMETERS");
        for (IElement ele : sensor) {
            ele.click();
            appPage.pause(1000);
            uiAction.sendKeys(Keys.ARROW_DOWN);
            uiAction.sendKeys(Keys.ENTER);
        }
        Assert.assertTrue(!uiAction.getElements(BUTTON, PAGE, "CREATE_ALERTRULE_CREATE_CANCEL").get(1).isEnabled());
        uiAction.getElement(INPUT, PAGE, "CREATE_ALERTUI_THRESHOLD").sendKeys("123");
        uiAction.getElement(INPUT, PAGE, "ALERT_PARAM_DURATION").sendKeys("123");
    }
    @Override
    public void createAlertButton() {
        appPage.pause(3000);
        uiAction.getElements(BUTTON, PAGE, "CREATE_ALERTRULE_CREATE_CANCEL").get(1).scrollIntoView();
        uiAction.getElements(BUTTON, PAGE, "CREATE_ALERTRULE_CREATE_CANCEL").get(1).jsClick();
        uiAction.getBrowser().switchToMainWindow();
    }
    @Override
    public void searchAlertinUI() {
     appPage.pause(2000);
     uiAction.getElement(INPUT,PAGE,"ALERT_SEARCH_UI").sendKeys("Cyborg");
    }
    @Override
    public void deleteAlert() {
        try {
            uiAction.getElements(BUTTON, PAGE, "ALERT_DELETE_OPTIONS").get(1).jsClick();
            appPage.pause(1000);
            if (uiAction.getElement(BUTTON, PAGE, "ALERT_DELETE_BUTTON").isDisplayed()) {
                uiAction.getElement(BUTTON, PAGE, "ALERT_DELETE_BUTTON").jsClick();
                appPage.pause(2000);
                if(uiAction.getElement(DIV,PAGE,"CONFIRM_ALERT_DELETION").isDisplayed())
                { uiAction.getElement(DIV,PAGE,"CONFIRM_ALERT_DELETE_BUTTON").jsClick();
                }
                else{
                    Assert.fail("Alert Delete Confirmation Pop Up is missing ");
                }
                uiAction.getBrowser().switchToMainWindow();
            }
        } catch (Exception e) {
            Assert.fail("System is unable to delete the rule ");
        }
    }
    @Override
    public void searchedAlertResult() {
            appPage.pause(3000);
            try {
                if (uiAction.getElements(DIV,PAGE,"ALERT_SEARCH_RESULT").size() > 0) {
                    System.out.println("There are total" + uiAction.getElements(DIV,PAGE,"ALERT_SEARCH_RESULT").size() + " search items");
                }
            } catch (Exception e) {
                Assert.fail("There are no search items with given keyword");
            }
        }
    @Override
    public void clickOnAddalertButton(){
        try {
            uiAction.getElement(BUTTON, PAGE, "CREATE_ALERT_BUTTON").jsClick();
            appPage.pause(2000);
            uiAction.getBrowser().switchToMainWindow();
        }catch (Exception e)
        {
            Assert.fail("Add alert button is missing the UI");
        }
    }
}
