package com.bh.at.page_actions;

import static com.bh.at.core.DriverManager.ElementType.*;
import static com.bh.at.main.AppConfig.getEnvParam;
import static com.bh.at.tester.BaseConstants.*;
import static com.bh.at.tester.BaseTester.uiAction;

import com.bh.at.iuiutil.IFrame;
import com.bh.icommonallutil.IBaseException;
import com.bh.icommonallutil.IEXCELO;
import com.bh.xlxmlpdfutil.CyXlXmlPdfUtilFactory;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bh.at.core.DriverManager;
import com.bh.at.page_actions.iActions.ICommonUI;
import com.bh.at.uiutil.CyBrowserPage;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class CommonUIActions implements ICommonUI {

  private static final Logger LOG = LoggerFactory.getLogger(CommonUIActions.class);
  private final String url = getEnvParam("data_UI/url", null);
  public static final int defaultTimeout = 10000;
  public static CyBrowserPage appPage = null;
  private final String HOME_PAGE = "home";
  private static CyXlXmlPdfUtilFactory xxpuf;
  private final String auguryurl = getEnvParam("data_Augury/url", null);
  private final String MAPSPAGE = "maps";
  private final String SUMMARYPAGE = "summary";
  private final String page2 = "newLogin";
  @Override
  public void launchBrowser() {
    appPage = uiAction = new DriverManager();
    appPage.launch(url);
    LOG.info("Browser launched successfully.");
  }
  @Override
  public void search() {

    uiAction.getElement(INPUT, page2, "GOOGLEINPUT").waitForElementTobeDisplayed(8000);
    uiAction.setupDefaultWait();
    uiAction.getElement(INPUT, page2, "GOOGLEINPUT").sendKeys("ABCD");

  }

  @Override
  public void logout() {

  }

  @Override
  public void launchApplication() {

  }

  @Override
  public void quitBrowser() {
    appPage.quit();
  }

  public void auguryLaunchApplication() {
    appPage = uiAction = new DriverManager();
    appPage.launch(auguryurl);
    LOG.info("Browser launched successfully.");
  }

  @Override
  public void clickStatusActionBarItem() {
    uiAction.getElement(BUTTON,MAPSPAGE,"MAP_MENU").click();
  }

  @Override
  public void clickSummaryTab() {
    uiAction.getElement(BUTTON,SUMMARYPAGE,"SUMMARY_TAB").waitForElementTobeDisplayed(20000);
    uiAction.getElement(BUTTON,SUMMARYPAGE,"SUMMARY_TAB").click();
  }

  @Override
  public void performAssetMappingFromDifferentSourceSystems(String project) throws IBaseException {

    uploadExcelFileForMapping();
    appPage.getBrowser().reloadUI();
  }

  @Override
  public void AssetUnmappingFromDifferentSourceSystems(String project) throws IBaseException {

  }


  private void uploadExcelFileForMapping() {
    ((IFrame) uiAction.getElement(FRAME, HOME_PAGE, "HIERARCHY_PANEL_FRAME")).setAsCurrent();
    if(isFileDownloadedInDownloadFolder() == true){
      uiAction.getElement(INPUT, HOME_PAGE, "MAP_ASSETS_CHOOSE_FILE_BUTTON").sendKeys(FileSystems.getDefault().getPath(".").toAbsolutePath()+"/downloads/System 1 APM Mapping.xlsx");
    } else{
      uiAction.getElement(INPUT, HOME_PAGE, "MAP_ASSETS_CHOOSE_FILE_BUTTON").sendKeys(FileSystems.getDefault().getPath(".").toAbsolutePath()+"/System 1 APM Mapping.xlsx");
    }
    boolean successMessage = uiAction.getElement(TEXT, HOME_PAGE, "NODES_MAPPED_SUCCESSFULLY_MESSAGE").waitForElementTobeDisplayed(60);
    Assert.assertTrue("Failed to map nodes", successMessage);
    appPage.getBrowser().switchToMainWindow();
    //uiAction.getElement(HOME_PAGE, "AssetMenu_ICON").sendKeys(Keys.ESCAPE);
    appPage.pause(5000);
  }


  public boolean isFileDownloadedInDownloadFolder(){
    File system1APMMappingFile = new File(FileSystems.getDefault().getPath(".").toAbsolutePath()+"/downloads/System 1 APM Source Systems.xlsx");
    if(system1APMMappingFile.exists()){
      return true;
    }else{
      return false;
    }
  }


}