package com.bh.at.page_actions.iActions;

public interface IRiskRegister {


    public void verifyAddNewRiskButton();

    public void clickOnAddNewRiskButtonN();

    public void verifyCreateRiskPageAndRiskDetailsDropdownTitles();

    public void selectRiskDetailsDropDownValues();

    public void selectRiskSeverity();

    public void verifyAndSelectMitigationDetails();

    public void verifyAndSelectRecoveryInformation();

    public void addCommentsForRisk();

    public void validateCancelButton();

    public void verifyAndSelectCreateRiskButton();

    public void selectCreateRiskButtonWithoutRequiredFields();

    public void verifyRequiredFieldMessage();

    public void verifyAndSelectTheMeatballMenu();

    public void verifyDeleteAndUpdateOptionUnderMeatBallMenu();

    void selectDeleteOption(String Option, String noOfItems);
}
