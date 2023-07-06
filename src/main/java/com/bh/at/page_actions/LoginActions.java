package com.bh.at.page_actions;

import com.bh.at.iuiutil.IFrame;
import com.bh.at.page_actions.iActions.ILogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.bh.at.core.DriverManager.ElementType.*;
import static com.bh.at.main.AppConfig.getEnvParam;
import static com.bh.at.page_actions.CommonUIAction.appPage;
//import static com.bh.at.page_actions.CommonUIActions.appPage;
import static com.bh.at.page_actions.CommonUIAction.defaultTimeout;
import static com.bh.at.tester.BaseTester.*;


public class LoginActions implements ILogin {


    private static final Logger LOG = LoggerFactory.getLogger(LoginActions.class);
    private final String PAGE = "newLogin";
    private final String UN = getEnvParam("data_UI/username", null);
    private final String PASS =getEnvParam("data_UI/password","");

    @Override
    public void adminLogin() {

    }

    @Override
    public void HomePage() {

      LOG.info("Verifying default Homepage of Customer");
        appPage.pause(5000);
         appPage.getBrowser().reloadUI();
        if( appPage.getBrowser().getTitle().equals("CCUS Product"))
        {
            if(appPage.getCurrentUrl().contains("config-app")) {
                try {

                    ((IFrame) uiAction.getElement(FRAME, PAGE, "IFRAME_WRAPPER")).setAsCurrent();

                    while(!uiAction.getElement(DIV, PAGE, "DEFAULT_HOMEPAGE").isDisplayed())
                    {
                        appPage.getBrowser().reloadUI();
                    }
                    System.out.println( uiAction.getElement(DIV, PAGE, "DEFAULT_HOMEPAGE").getAttribute("data-key"));
                    LOG.info("User landed on Homepage succesfully");
                } catch (Exception e) {
                    LOG.info("User is not landed on Homepage as expected ");
                }
            }
            uiAction.getBrowser().switchToMainWindow();
        }

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

    @Override
    public void validateLogin() {
        LOG.info("com.bh.at.step.Login to CCUS Started ");
        appPage.pause(3000);
        if(appPage.getCurrentUrl().contains("login"))
        {
            uiAction.getElement(INPUT, PAGE, "USERNAME").sendKeys("Invalid Test");
            uiAction.getElement(INPUT, PAGE, "PASSWORD").sendKeys("Invalid Password");
            uiAction.getElement(BUTTON, PAGE, "NEXT_BUTTON").click();
            uiAction.getElement(TEXT,PAGE,"INVALID_LOGIN_ERROR").waitForElementTobeDisplayed();
            System.out.println(uiAction.getElement(TEXT,PAGE,"INVALID_LOGIN_ERROR").getText());

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
