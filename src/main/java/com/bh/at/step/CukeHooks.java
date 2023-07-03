package com.bh.at.step;

import com.bh.at.main.AppConfig;
import com.bh.at.tester.BaseTester;
import com.bh.at.utils.IAPMUtil;
import com.bh.icommonallutil.IJSONA;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bh.at.core.DriverManager;

import static com.bh.at.page_actions.CommonUIActions.appPage;
import static com.bh.at.tester.BaseTester.*;

public class CukeHooks {

    private static final Logger LOG = LoggerFactory.getLogger(CukeHooks.class);
    private static int pixCount = 0;

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("beforeScenario " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        LOG.info("Running After Scenario");
        /*try {
            if (scenario.isFailed() && appPage != null) {
                IJSONA logs = appPage.getBrowser().getDevTools().getLatestBrowserLogs(100);
                LOG.error(" ***ERROR CONSOLE***", logs);
                final byte[] screenshot = IAPMUtil.pngBytesToJpgBytes(((TakesScreenshot) DriverManager.driver).getScreenshotAs(OutputType.BYTES));
                scenario.attach(screenshot, "image/png", "Cyborg" + ++pixCount);
                scenario.attach(String.valueOf(logs), "text/plain", "CONSOLE LOGS");
            }
        } catch (Exception e) {
            LOG.error("Error while accessing failed scenario details " + e.getMessage());
        } finally {


            }
            if(!currentTenant.equals(defaultTenant)) {
                currentTenant = defaultTenant;
                actions.getCommonAPIActions().clearTokenHeaders();
            }*/

        if (appPage != null) {
            appPage.quit();
            appPage = null;
            DriverManager.driver = null;
        }
    }
}