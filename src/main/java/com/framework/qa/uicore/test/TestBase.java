package com.framework.qa.uicore.test;

import com.framework.qa.uicore.page.PageBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.framework.qa.uicore.driver.DriverConnection;
import com.framework.qa.utils.exception.FrameworkException;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

/**
 * TestBase.java - Suite initializer and terminator class
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_23_2016
 * @since 04/23/2016.
 */
public class TestBase {
    final static Logger log = Logger.getLogger(TestBase.class);
    private RemoteWebDriver driver;
    public RemoteWebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void suiteInitialize(){

    }

    @BeforeMethod(alwaysRun = true)
    public void initializeBaseSetup() throws FrameworkException {
        driver = DriverConnection.getDeriverInstance();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws FrameworkException {
        DriverConnection.closeDriver();
        PageBase.flushExtentReport();
    }

    @AfterSuite
    public void suiteTerminate(){

    }
}
