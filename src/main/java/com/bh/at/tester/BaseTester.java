package com.bh.at.tester;

import com.bh.at.core.DriverManager;
import com.bh.at.page_actions.GlobalActions;

public class BaseTester {

  public static GlobalActions   actions = new GlobalActions();
  public static String currentTenant = "S1E";
  public static String currentUser = null;
  public static DriverManager uiAction = null;
  public static String defaultTenant = "S1E";

}
