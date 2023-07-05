package com.bh.at.page_actions;


import com.bh.at.iapiutil.IAPIHelper;
import com.bh.at.page_actions.iActions.*;
import com.bh.icommonallutil.IBaseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalActions implements ICommonUI, ILogin{

    private ICommonUI commonUIAction;
    private ILogin loginActions;

    public ICommonUI getCommonUIActions() {
        return commonUIAction;
    }

    public void setCommonUIActions(ICommonUI commonUIAction) {
        this.commonUIAction = commonUIAction;
    }

    protected Map<String, Object> oldFilterOptions = new HashMap<>();
    protected Map<String, Object> newFilterOptions = new HashMap<>();

    //UI override methods to be place below************************************************************

    @Override
    public void login() {
        this.loginActions.login();
    }

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

    /*@Override
    public void logOut() {
        this.loginActions.logOut();
    }*/

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


}


