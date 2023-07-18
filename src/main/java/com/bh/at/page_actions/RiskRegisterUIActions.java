package com.bh.at.page_actions;

import com.bh.at.iuiutil.IElement;
import com.bh.at.iuiutil.IFrame;
import com.bh.at.page_actions.iActions.IRiskRegister;
import com.bh.at.uiutil.CyBrowserPage;
import com.bh.at.uiutil.CyElements;
import com.ctc.wstx.shaded.msv_core.datatype.xsd.BuiltinAtomicType;
import io.cucumber.java.an.E;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.xpath.XPath;

import static com.bh.at.main.AppConfig.getEnvParam;
import static com.bh.at.core.DriverManager.ElementType.*;
import static com.bh.at.page_actions.CommonUIAction.appPage;
import static com.bh.at.tester.BaseTester.uiAction;

public class RiskRegisterUIActions implements IRiskRegister {

    //public static CyBrowserPage appPage = null;
    public static final int defaultTimeout = 5000;

    private final String RISKREGISTERPAGE = "RiskManagement";
    private final String ASSETPAGE = "assetHierarchy";
    private static final Logger LOG = LoggerFactory.getLogger(RiskRegisterUIActions.class);

    private final String url = getEnvParam("data_UI/url", null);

    private CyElements<IElement> Parentnodes;


    @Override
    public void verifyAddNewRiskButton() {

        LOG.info("verify Risk Management view in CCUS APP");
        appPage.pause(defaultTimeout);
        try {
            ((IFrame)uiAction.getElement(FRAME,RISKREGISTERPAGE,"IFRAME_WRAPPER")).setAsCurrent();

            if(!uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_HOMEPAGE").isDisplayed())

            uiAction.getElement(DIV,RISKREGISTERPAGE,"ADD_NEW_RISK_BUTTON").isDisplayed();
//            uiAction.getElement(BUTTON,RISKREGISTERPAGE,"ADD_NEW_RISK_BUTTON").click();
//            appPage.pause(2000);
        }catch (Exception e){
            LOG.info("-------Risk Management view is not available or integrated to the application------------ ");
            Assert.fail("Risk Management UI is not available");
        }
    }

    @Override
    public void clickOnAddNewRiskButtonN() {
        LOG.info("Clicking on Add new Risk Button");
        appPage.pause(defaultTimeout);
        try {
            uiAction.getElement(BUTTON,RISKREGISTERPAGE,"ADD_NEW_RISK_BUTTON").click();
            appPage.pause(2000);
        } catch (Exception e){
            Assert.fail("Not able to click on Add new Risk button");
        }

    }

    @Override
    public void verifyCreateRiskPageAndRiskDetailsDropdownTitles() {
        LOG.info("Verifing that user is move to Create Risk page");
        appPage.pause(2000);
        try {
            uiAction.getElement(DIV, RISKREGISTERPAGE, "CREATE_RISK_PAGE").isDisplayed();
            CyElements<IElement> riskDropDownLableList =  uiAction.getElements(DIV, RISKREGISTERPAGE, "RISK_DROPDOWN_LABEL_LIST");
            riskDropDownLableList.get(0).getText();

            String[] validationList = {"Service Company\n*", "Risk Category\n*", "Risk\n*", "Risk Effect\n*", "Hazard\n*", "Control & their Ranks\n*", "Asset\n*"};
            int counter = 0;
            for (IElement ele : riskDropDownLableList){
                LOG.info("Validating the dropdown header text : "+ ele.getText());
                Assert.assertEquals("Validating the Risk Details header", ele.getText(), validationList[counter]);
                counter++;
            }

        }catch (Exception e){
            Assert.fail("Create Risk page is not loaded successfully ");
        }

    }

    @Override
    public void selectRiskDetailsDropDownValues() {
        LOG.info("Selecting the dropdown values for RiskDetail section");
        appPage.pause(defaultTimeout);

        try {
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_SERVICE_COMPANY_DROPDOWN").click();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"SELECT_SERVICE_COMPANY_IPS").jsClick();
            appPage.pause(1000);
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_CATEGORY").click();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_CATEGORY_MECHANICAL").jsClick();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_CATEGORY").clickAt(1,10);
            appPage.pause(1000);
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK").click();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_SIDE_WALL_COLLAPSING").jsClick();
            appPage.pause(1000);
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_EFFECT").click();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_EFFECT_WALL_CLOSURE").jsClick();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_EFFECT").clickAt(1,10);
            appPage.pause(1000);
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_HAZARD").click();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_HAZARD_CASING_1").jsClick();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_HAZARD").clickAt(1,10);
            appPage.pause(1000);
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_CONTROL_AND_THEIR_RANKS").click();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_C&R_PRESSURE").jsClick();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_C&R_RANK").click();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_C&R_RANK_VALUE_1").jsClick();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_CONTROL_AND_THEIR_RANKS").clickAt(1,10);
            appPage.pause(1000);
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_SELECT_ASSERT").click();
            appPage.pause(1000);
            CyElements<IElement> assets = uiAction.getElements(BUTTON, ASSETPAGE, "ASSET_EXPANDED_NODE");
            for (IElement ele : assets) {

                if (ele.isDisplayed()) {
                    ele.jsClick();
                    uiAction.moveTo(1, 1);
                    uiAction.click();
                    break;
                }
            }
        }catch (Exception e){
            Assert.fail("Not able to select the risk details dropdown values with error message "+e.getMessage());
        }
    }


    @Override
    public void selectRiskSeverity() {
        LOG.info("Selecting the risk Severity as LOW, MEDIUM or HIGH");
        appPage.pause(1000);
        try {
            uiAction.getElement(DIV, RISKREGISTERPAGE, "RISK_SEVERITY_LABEL").isDisplayed();
            uiAction.getElement(DIV, RISKREGISTERPAGE, "RISK_SEVERITY_HIGH").click();

        }catch (Exception e){
            Assert.fail("Not able to select the Severity "+ e.getMessage());
        }

    }

    @Override
    public void verifyAndSelectMitigationDetails() {

    }

    @Override
    public void verifyAndSelectRecoveryInformation() {

    }

    @Override
    public void addCommentsForRisk() {
        LOG.info("Adding comment for the new risk created");
        uiAction.getElement(INPUT, RISKREGISTERPAGE ,"RISK_COMMENTS").sendKeys("This is the Automated Comment");
        appPage.pause(1000);
    }

    @Override
    public void validateCancelButton() {
        LOG.info("validating the cancel button");
        try {
            uiAction.getElement(BUTTON, RISKREGISTERPAGE, "CANCEL_RISK_BUTTON").isDisplayed();
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Override
    public void verifyAndSelectCreateRiskButton() {
        LOG.info("Select Create risk Button to create a new risk");
        appPage.pause(1000);
        try {
            uiAction.getElement(BUTTON,RISKREGISTERPAGE,"CREATE_RISK_BUTTON").isDisplayed();
            uiAction.getElement(BUTTON,RISKREGISTERPAGE,"CREATE_RISK_BUTTON").jsClick();
            appPage.pause(2000);
            uiAction.getElement(BUTTON,RISKREGISTERPAGE,"CREATE_RISK_BUTTON_CONFIRM").jsClick();
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }

    }

    @Override
    public void selectCreateRiskButtonWithoutRequiredFields() {
        LOG.info("selecting create Risk button without some required fields");
        appPage.pause(1000);
        try {
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_SERVICE_COMPANY_DROPDOWN").click();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"SELECT_SERVICE_COMPANY_IPS").jsClick();
            appPage.pause(1000);
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_CATEGORY").click();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_CATEGORY_MECHANICAL").jsClick();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_CATEGORY").clickAt(1,10);
            appPage.pause(1000);
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK").click();
            uiAction.getElement(DIV,RISKREGISTERPAGE,"RISK_SIDE_WALL_COLLAPSING").jsClick();
            appPage.pause(1000);


        }catch (Exception e){
            Assert.fail(e.getMessage());
        }

    }

    @Override
    public void verifyRequiredFieldMessage() {

        LOG.info("Click on Create Risk button and verify the error message");
        appPage.pause(1000);
        try {
            uiAction.getElement(BUTTON,RISKREGISTERPAGE,"CREATE_RISK_BUTTON").isDisplayed();
            uiAction.getElement(BUTTON,RISKREGISTERPAGE,"CREATE_RISK_BUTTON").jsClick();

            uiAction.getElement(DIV, RISKREGISTERPAGE, "REQUIRED_FIELD_ERROR_MESSAGE").isDisplayed();
            LOG.info("Error message is displayed successfully now click cancel button");
            uiAction.getScreenshotAsImage();
            appPage.pause(2000);
            uiAction.getElement(BUTTON, RISKREGISTERPAGE,"CANCEL_RISK_BUTTON").jsClick();


        }catch (Exception e){
            Assert.fail(e.getMessage());
        }

    }
}
