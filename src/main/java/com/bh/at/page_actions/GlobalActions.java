package com.bh.at.page_actions;


import com.bh.at.page_actions.iActions.*;
import com.bh.icommonallutil.IBaseException;

import java.util.HashMap;
import java.util.Map;

public class GlobalActions implements ICommonUI, ILogin, IRiskRegister{

    private ICommonUI commonUIAction;
    private ILogin loginActions;



    private IRiskRegister riskRegister;

    public IRiskRegister getRiskRegister() {
        return riskRegister;
    }

    public void setRiskRegister(IRiskRegister riskRegister) {
        this.riskRegister = riskRegister;
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
    public void verifyAddNewRiskButton() {
        this.riskRegister.verifyAddNewRiskButton();

    }

    @Override
    public void clickOnAddNewRiskButtonN() {
        this.riskRegister.clickOnAddNewRiskButtonN();

    }

    @Override
    public void verifyCreateRiskPageAndRiskDetailsDropdownTitles() {
        this.riskRegister.verifyCreateRiskPageAndRiskDetailsDropdownTitles();

    }

    @Override
    public void selectRiskDetailsDropDownValues() {
        this.riskRegister.selectRiskDetailsDropDownValues();

    }

    @Override
    public void selectRiskSeverity() {
        this.riskRegister.selectRiskSeverity();

    }

    @Override
    public void verifyAndSelectMitigationDetails() {

    }

    @Override
    public void verifyAndSelectRecoveryInformation() {

    }

    @Override
    public void addCommentsForRisk() {
        this.riskRegister.addCommentsForRisk();

    }

    @Override
    public void validateCancelButton() {
        this.riskRegister.validateCancelButton();

    }

    @Override
    public void verifyAndSelectCreateRiskButton() {
        this.riskRegister.verifyAndSelectCreateRiskButton();

    }

    @Override
    public void selectCreateRiskButtonWithoutRequiredFields() {
        this.riskRegister.selectCreateRiskButtonWithoutRequiredFields();
    }

    @Override
    public void verifyRequiredFieldMessage() {
        this.riskRegister.verifyRequiredFieldMessage();

    }
}


