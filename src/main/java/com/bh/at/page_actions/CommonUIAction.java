package com.bh.at.page_actions;

import com.bh.at.core.DriverManager;
import com.bh.at.page_actions.iActions.ICommonUI;
import com.bh.at.uiutil.CyBrowserPage;
import com.bh.icommonallutil.IBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.bh.at.core.DriverManager.ElementType.BUTTON;
import static com.bh.at.core.DriverManager.ElementType.INPUT;
import static com.bh.at.main.AppConfig.getEnvParam;
import static com.bh.at.tester.BaseTester.uiAction;


public class CommonUIAction implements ICommonUI {

    public static CyBrowserPage appPage = null;
    public static final int defaultTimeout = 10000;

    private final String PAGE = "newLogin";

    private static final Logger LOG = LoggerFactory.getLogger(CommonUIAction.class);

    private final String url = getEnvParam("data_UI/url", null);




    @Override
    public void launchApplication() {

        appPage = uiAction = new DriverManager();
        appPage.launch(url);
        LOG.info("Browser launched successfully.");


    }


    @Override
    public void launchBrowser() {



    }

    @Override
    public void quitBrowser() {

    }

    @Override
    public void clickStatusActionBarItem() {

    }

    @Override
    public void clickSummaryTab() {

    }

    @Override
    public void search() {

    }

    @Override
    public void logout() {
        LOG.info("User logged out succesfully ");
        appPage.pause(defaultTimeout);
        appPage.getBrowser().switchToMainWindow();
        uiAction.getElement(INPUT, PAGE, "AVATAR").click();
        uiAction.getElement(INPUT, PAGE, "LOGOUT").waitForElementTobeDisplayed();
        uiAction.getElement(INPUT, PAGE, "LOGOUT").jsClick();
        uiAction.getElement(BUTTON, PAGE, "CONFIRM_LOGOUT").jsClick();

    }



    @Override
    public void auguryLaunchApplication() {

    }

    @Override
    public void performAssetMappingFromDifferentSourceSystems(String project) throws IBaseException {

    }

    @Override
    public void AssetUnmappingFromDifferentSourceSystems(String project) throws IBaseException {

    }

}
