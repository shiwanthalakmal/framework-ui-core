package com.framework.qa.uicore.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

/**
 * TestNGListener.java -  TestNge event listener
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_22_2016
 * @since 04/22/2016.
 */
public class TestNGListener implements ITestListener, ISuiteListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestNGListener.class);
    private String strLoggerMsg;
    private String strClassName;


    /**
     * Invoked each time before a test will be invoked.
     *
     * @param result test method details
     */
    @Override
    public void onTestStart(ITestResult result) {
        strLoggerMsg = ("Test Started Running: " + "---- " + result.getMethod().getMethodName() + " ---- at:" + result.getStartMillis());
        LOGGER.info(strLoggerMsg);

    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result test method details
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test '" + result.getName() + "' --- PASSED" + "\n\n\n");
    }

    /**
     * Invoked each time a test fails and attach details to testNg report
     *
     * @param result test method results
     */
    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Test '" + result.getName() + "' --- FAILED" + "\n\n\n");
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result test method result
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.info("Test '" + result.getName() + "' --- SKIPPED" + "\n\n\n");

    }

    /**
     * Invoked after all the tests have run and
     * all their Configuration methods have been called.
     *
     * @param context
     */
    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("Completed Executing Test : " + "---- " + context.getName());

    }

    /**
     * Invoked each time a method fails but has been annotated with successPercentage
     * and this failure still keeps it within the success percentage requested.
     *
     * @param result
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    /**
     * Invoked after the test class is instantiated and before
     * any configuration method is called.
     *
     * @param context
     */
    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("About To Begin Executing : " + "---- " + context.getName() + " ----");

    }

    /**
     * This belongs to ISuiteListener and will execute, once the Suite is finished
     *
     * @param arg0
     */
    @Override
    public void onFinish(ISuite arg0) {
        LOGGER.info("About To End Executing Suite : " + arg0.getName());

    }

    /**
     * This belongs to ISuiteListener and will execute before the Suite start
     *
     * @param arg0
     */
    @Override
    public void onStart(ISuite arg0) {
        LOGGER.info("About To Begin Executing Suite : " + arg0.getName());
    }

}
