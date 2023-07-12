package com.bh.at.page_actions.iActions;

import com.bh.icommonallutil.IBaseException;

public interface ICommonUI {

  public void launchBrowser();

  public void quitBrowser();

  void clickStatusActionBarItem();

  void clickSummaryTab();

  void search();

  void logout();

  void launchApplication();

  public void auguryLaunchApplication();
  public void performAssetMappingFromDifferentSourceSystems(String project) throws IBaseException;
  public void AssetUnmappingFromDifferentSourceSystems(String project) throws IBaseException;

  public void verifyAssetHierarchyview();

  public void navigateToAssetHierarchyview();

  public void serachInAssetHierarchyview();

}
