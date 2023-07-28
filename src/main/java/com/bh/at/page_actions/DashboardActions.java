package com.bh.at.page_actions;

import com.bh.at.page_actions.iActions.IDashboardUi;

import static com.bh.at.core.DriverManager.ElementType.DIV;
import static com.bh.at.page_actions.CommonUIAction.appPage;
import static com.bh.at.tester.BaseTester.uiAction;

public class DashboardActions implements IDashboardUi {
    private final String PAGE = "dashBoard";
    @Override
    public void DashboardInsights() {
       appPage.pause(2000);
       if(uiAction.getElement(DIV,PAGE,"DASHBOARD_INSIGHT").isDisplayed())
       {

       };
    }

    @Override
    public void AssetInsights() {

    }

    @Override
    public void navigateToAssetInsights() {

    }
}
