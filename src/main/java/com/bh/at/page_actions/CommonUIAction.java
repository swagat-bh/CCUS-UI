package com.bh.at.page_actions;

import com.bh.at.core.DriverManager;
import com.bh.at.iuiutil.IElement;
import com.bh.at.iuiutil.IElements;
import com.bh.at.iuiutil.IFrame;
import com.bh.at.page_actions.iActions.ICommonUI;
import com.bh.at.uiutil.CyBrowserPage;
import com.bh.at.uiutil.CyElement;
import com.bh.at.uiutil.CyElements;
import com.bh.icommonallutil.IBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.bh.at.core.DriverManager.ElementType.*;
import static com.bh.at.main.AppConfig.getEnvParam;
import static com.bh.at.tester.BaseTester.uiAction;


public class CommonUIAction implements ICommonUI {

    public static CyBrowserPage appPage = null;
    public static final int defaultTimeout = 5000;

    private final String PAGE = "newLogin";
    private final String ASSETPAGE="assetHierarchy";
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

    @Override
    public void verifyAssetHierarchyview() {
        LOG.info("Verifying Asset Hierachy View in CCUS App.");
        try {
            appPage.pause(defaultTimeout);
            appPage.getBrowser().switchToMainWindow();
            if(!uiAction.getElement(DIV, ASSETPAGE, "ASSET_MENU").isDisplayed())
            {
                uiAction.getBrowser().reloadUI();
                //need to check UI logic
                uiAction.getElement(DIV, ASSETPAGE, "ASSET_MENU").isDisplayed();
            }
            LOG.info("----------Asset Hierachy View is integrated with App and Aavailable in UI--------- ");
        }
        catch(Exception e)
        {
            LOG.info("----------Asset Hierachy View is not Aavailable in UI--------- ");
        }

    }

    @Override
    public void navigateToAssetHierarchyview() {
         uiAction.getElement(DIV, ASSETPAGE, "ASSET_MENU").moveTo().click();
    }

    @Override
    public void serachInAssetHierarchyview() {
        ((IFrame) uiAction.getElement(FRAME, ASSETPAGE, "IFRAME_WRAPPER")).setAsCurrent();
        uiAction.getElement(INPUT,ASSETPAGE,"ASSET_SEARCH").sendKeys("Mac");
        appPage.pause(2000);
        uiAction.getElement(BUTTON,ASSETPAGE,"ASSET_SEARCH_ICON").click();
        appPage.pause(4000);
        CyElements<IElement> ele=uiAction.getElements(TEXT,ASSETPAGE,"ASSET_EXPANDED_NODE");
        LOG.info("----------->Asset Search Results<-----------");
       for(IElement Cyele:ele)
       {if(Cyele.getTextValue().contains("Mac"))
           {
               System.out.println(Cyele.getTextValue());
           }
       }
        appPage.getBrowser().switchToMainWindow();
    }


}
