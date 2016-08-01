package com.framework.qa.uicore.driver;

/**
 * Browser.java -  Collection of web browsers enum class
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_22_2016
 * @since 04/22/2016.
 */
public enum Browser {
    FIREFOX("firefox"), CHROME("chrome"), SAFARI("safari"), EXPLORE("ie"), EDGE("edge");

    private String statusCode;

    private Browser(String s) {
        statusCode = s;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
