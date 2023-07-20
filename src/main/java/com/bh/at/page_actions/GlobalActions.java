package com.bh.at.page_actions;


import com.bh.at.page_actions.iActions.IAlertmanagementUI;
import com.bh.at.page_actions.iActions.ICommonUI;
import com.bh.at.page_actions.iActions.ILogin;
import com.bh.icommonallutil.IBaseException;

import java.util.HashMap;
import java.util.Map;

public class GlobalActions implements ICommonUI, ILogin, IAlertmanagementUI {
    private ICommonUI commonUIAction;
    private ILogin loginActions;
    private IAlertmanagementUI AlertmngmntActions;
    public IAlertmanagementUI getAlertmngmntActions() {
        return AlertmngmntActions;
    }
    public void setAlertmngmntActions(IAlertmanagementUI alertmngmntActions) {
        this.AlertmngmntActions = alertmngmntActions;
    }
    public ICommonUI getCommonUIActions() {
        return commonUIAction;
    }
    public void setCommonUIActions(ICommonUI commonUIAction) {
        this.commonUIAction = commonUIAction;
    }
    protected Map<String, Object> oldFilterOptions = new HashMap<>();
    protected Map<String, Object> newFilterOptions = new HashMap<>();
    //UI override methods to be place below************************************************************
    public ILogin getLogin() {
        return loginActions;
    }
    public void setLogin(ILogin login) {
        this.loginActions = login;
    }
    @Override
    public void adminLogin() {
        this.loginActions.adminLogin();
    }
    @Override
    public void HomePage() {
        this.loginActions.HomePage();
    }
    @Override
    public void userlogin() {
        this.loginActions.userlogin();
    }
    @Override
    public void launchBrowser() {
        this.commonUIAction.launchBrowser();
    }
    @Override
    public void quitBrowser() {
        this.commonUIAction.quitBrowser();
    }
    public void auguryLaunchApplication() {
        this.commonUIAction.auguryLaunchApplication();
    }
    @Override
    public void clickStatusActionBarItem() {
        this.commonUIAction.clickStatusActionBarItem();
    }
    @Override
    public void clickSummaryTab() {
        this.commonUIAction.clickSummaryTab();
    }
    @Override
    public void performAssetMappingFromDifferentSourceSystems(String project) throws IBaseException {
        this.commonUIAction.performAssetMappingFromDifferentSourceSystems(project);
    }
    @Override
    public void AssetUnmappingFromDifferentSourceSystems(String project) throws IBaseException {
        this.commonUIAction.AssetUnmappingFromDifferentSourceSystems(project);
    }
    @Override
    public void verifyAssetHierarchyview() {
        this.commonUIAction.verifyAssetHierarchyview();
    }
    @Override
    public void navigateToAssetHierarchyview() {
        this.commonUIAction.navigateToAssetHierarchyview();
    }
    @Override
    public void serachInAssetHierarchyview() {
        this.commonUIAction.serachInAssetHierarchyview();
    }
    public void search() {
        this.commonUIAction.search();
    }
    @Override
    public void logout() {
        this.commonUIAction.logout();
    }
    @Override
    public void launchApplication() {
        this.commonUIAction.launchApplication();
    }
    @Override
    public void validateLogin() {
        this.loginActions.validateLogin();
    }
    @Override
    public void navigateToAlertmngmntTab() {
       this.AlertmngmntActions.navigateToAlertmngmntTab();
    }
    @Override
    public void navigateTocreatealertui() {
        this.AlertmngmntActions.navigateTocreatealertui();
    }
    @Override
    public void createAlertButton() {
        this.AlertmngmntActions.createAlertButton();
    }
    @Override
    public void searchAlertinUI() {

        this.AlertmngmntActions.searchAlertinUI();
    }

    @Override
    public void deleteAlert() {
        this.AlertmngmntActions.deleteAlert();
    }

    @Override
    public void searchedAlertResult() {

        this.AlertmngmntActions.searchedAlertResult();
    }
    @Override
    public void clickOnAddalertButton() {

        this.AlertmngmntActions.clickOnAddalertButton();
    }
}


