package com.bh.at.page_actions;

import com.bh.at.iuiutil.IFrame;
import com.bh.at.page_actions.iActions.ILogin;
import com.bh.at.tester.BaseTester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.bh.at.core.DriverManager.ElementType.*;
import static com.bh.at.main.AppConfig.getEnvParam;
import static com.bh.at.page_actions.CommonUIAction.appPage;
//import static com.bh.at.page_actions.CommonUIActions.appPage;
import static com.bh.at.page_actions.CommonUIAction.defaultTimeout;
import static com.bh.at.tester.BaseConstants.*;
import static com.bh.at.tester.BaseTester.*;


public class LoginActions implements ILogin {


    private static final Logger LOG = LoggerFactory.getLogger(LoginActions.class);
    private final String PAGE = "newLogin";
    private final String UN = getEnvParam("data_UI/username", null);
    private final String PASS =getEnvParam("data_UI/password","");


    @Override
    public void login() {
        LOG.info("com.bh.at.step.Login to APM started");
        appPage.pause(3000);

        preLogin();

    }

    @Override
    public void adminLogin() {

    }


    @Override
    public void logOut() {
        appPage.pause(defaultTimeout);
        appPage.getBrowser().switchToMainWindow();

        System.out.println("Logout----");
        uiAction.getElement(INPUT, PAGE, "AVATAR").click();
        uiAction.getElement(INPUT, PAGE, "LOGOUT").waitForElementTobeDisplayed();
        uiAction.getElement(INPUT, PAGE, "LOGOUT").jsClick();
        uiAction.getElement(BUTTON, PAGE, "CONFIRM_LOGOUT").jsClick();
    }

    @Override
    public void userlogin() {

        LOG.info("com.bh.at.step.Login to CCUS Started ");
        appPage.pause(3000);
        if(appPage.getCurrentUrl().contains("login"))
        {
            uiAction.getElement(INPUT, PAGE, "USERNAME").sendKeys(getEnvParam("data_UI/username", ""));
            uiAction.getElement(INPUT, PAGE, "PASSWORD").sendKeys(getEnvParam("data_UI/password",""));
            uiAction.getElement(BUTTON, PAGE, "NEXT_BUTTON").click();

        }


    }

    private void preLogin() {
        if (appPage.getCurrentUrl().contains("login")) {
            uiAction.getElement(INPUT, PAGE, "PRELOGIN_USERNAME").sendKeys(UN);
            uiAction.getElement(INPUT, PAGE, "PRELOGIN_PASSWORD").sendKeys(PASS);
            uiAction.getElement(BUTTON, PAGE, "NEXT_BUTTON").click();
            if(uiAction.getElements(BUTTON, PAGE, "CONFIRM_COOKIE").get(0).waitForElementTobeDisplayed())
            {


                uiAction.getElements(BUTTON, PAGE, "CONFIRM_COOKIE").get(0).jsClick();
            }

        }
    }
    private void search(){

        uiAction.getElement(INPUT,PAGE,"GOOGLEINPUT").sendKeys("Test google ");
    }

}
