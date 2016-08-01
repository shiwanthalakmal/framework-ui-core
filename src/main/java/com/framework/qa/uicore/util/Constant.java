package com.framework.qa.uicore.util;

import com.relevantcodes.extentreports.ExtentReports;
import com.framework.qa.utils.exception.FrameworkException;
import com.framework.qa.utils.util.FrameworkProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

/**
 * Constant.java -  web based project constant variables
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_23_2016
 * @since 04/23/2016.
 */
public class Constant {
    private static final Logger LOGGER = LoggerFactory.getLogger(Constant.class);

    private static HashMap <String,String> hmLoadDataFromXml;

    private static final String ENVIRONMENT_TYPE_KEY = "environmentType";
    public static String ENVIRONMENT_TYPE;

    private static final String TESTLINK_MODE_KEY = "test-link";
    private static final String TESTLINK_URL_KEY = "test-link-url";
    private static final String TESTLINK_DEV_KEY = "test-link-key";
    private static final String TESTLINK_PRO_KEY = "test-link-project";
    private static final String TESTLINK_PLAN_KEY = "test-link-plan";
    private static final String TESTLINK_PLATFORM_KEY = "test-link-platform";
    private static final String TESTLINK_BUILD_KEY = "test-link-build";
    private static final String TESTLINK_PREFIX_KEY = "test-link-prefix";
    public static String TESTLINK_MODE;
    public static String TESTLINK_URL;
    public static String TESTLINK_DEV;
    public static String TESTLINK_PRO;
    public static String TESTLINK_PLAN;
    public static String TESTLINK_PLATFORM;
    public static String TESTLINK_BUILD;
    public static String TESTLINK_PREFIX;

    private static final String PROTOCOL_KEY = "protocol";
    private static final String SERVER_KEY = "server";
    private static final String PORT_KEY = "port";
    private static final String DIRECTORY_KEY = "directory";
    private static final String PAGE_KEY = "page";
    public static String PROTOCOL;
    public static String PORT;
    public static String SERVER;
    public static String DIRECTORY;
    public static String PAGE;

    private static final String TIMEOUT_IMPLICIT_KEY = "implicit";
    private static final String TIMEOUT_SCRIPT_KEY = "script";
    private static final String TIMEOUT_PAGELOAD_KEY = "pageload";
    private static final String TIMEOUT_ENV_KEY = "env-timeout";
    public static int TIMEOUT_IMPLICIT;
    public static int TIMEOUT_SCRIPT;
    public static int TIMEOUT_PAGELOAD;
    public static int TIMEOUT_ENV;

    private static final String BROWSER_NAME_KEY = "name";
    private static final String DRIVER_TYPE_KEY = "driver";
    private static final String DRIVER_LOCATION_KEY = "location";
    public static String BROWSER_NAME;
    public static String DRIVER_TYPE;
    public static String DRIVER_LOCATION;

    private static final String CONTENT_MISSING_MSG_FF_KEY = "contentnotfoundff";
    private static final String CONTENT_MISSING_MSG_GC_KEY = "contentnotfoundgc";
    private static final String CONTENT_MISSING_MSG_IE_KEY = "contentnotfoundie";
    private static final String TIMEOUT_MSG_FF_KEY = "timeoutff";
    private static final String TIMEOUT_MSG_GC_KEY = "timeoutgc";
    private static final String TIMEOUT_MSG_IE_KEY = "timeoutie";
    public static String CONTENT_MISSING_FF;
    public static String CONTENT_MISSING_GC;
    public static String CONTENT_MISSING_IE;
    public static String TIMEOUT_FF;
    public static String TIMEOUT_GC;
    public static String TIMEOUT_IE;

    public static final String GRID_MODE_KEY ="grid-mode";
    public static final String OS_NAME_KEY ="os-type";
    public static String GRID_MODE;
    public static String OS_NAME;

    public static final String DOWNLOAD_PATH_KEY="downloadpath";
    public static String DOWNLOAD_PATH;

    public static final String ELEMENT_PATH = "config/ui/elements/";
    public static final String SCREENSHOT_BROWSER_PATH = "screenshots/browser/";
    public static final String SCREENSHOT_ELEMENT_PATH = "screenshots/element/";
    public static final String HTML_PATH = "html/";

    static {

        try {
            loadXmlProperties();
        } catch (FrameworkException e) {
            LOGGER.error("Error ! Can't load environment.xml file - " + e.getMessage());
        }
    }

    /**
     * Convert environment.xml values as globally access variables
     * @throws FrameworkException
     */
    public static void loadXmlProperties() throws FrameworkException {
        FrameworkProperties loadProperties = new FrameworkProperties();
        hmLoadDataFromXml = new HashMap<String, String>();
        URL inputStream = null;
        inputStream = Constant.class.getClassLoader().getResource("config/ui/environment.xml");
        hmLoadDataFromXml = loadProperties.readProjEnvConfig(inputStream.getPath());
        try {
            ENVIRONMENT_TYPE = (System.getProperty(ENVIRONMENT_TYPE_KEY) != null ? System.getProperty(ENVIRONMENT_TYPE_KEY) : hmLoadDataFromXml.get(ENVIRONMENT_TYPE_KEY));

            TESTLINK_MODE = (System.getProperty(TESTLINK_MODE_KEY) != null ? System.getProperty(TESTLINK_MODE_KEY) : hmLoadDataFromXml.get(TESTLINK_MODE_KEY));
            TESTLINK_URL = (System.getProperty(TESTLINK_URL_KEY) != null ? System.getProperty(TESTLINK_URL_KEY) : hmLoadDataFromXml.get(TESTLINK_URL_KEY));
            TESTLINK_DEV = (System.getProperty(TESTLINK_DEV_KEY) != null ? System.getProperty(TESTLINK_DEV_KEY) : hmLoadDataFromXml.get(TESTLINK_DEV_KEY));
            TESTLINK_PRO = (System.getProperty(TESTLINK_PRO_KEY) != null ? System.getProperty(TESTLINK_PRO_KEY) : hmLoadDataFromXml.get(TESTLINK_PRO_KEY));
            TESTLINK_PLAN = (System.getProperty(TESTLINK_PLAN_KEY) != null ? System.getProperty(TESTLINK_PLAN_KEY) : hmLoadDataFromXml.get(TESTLINK_PLAN_KEY));
            TESTLINK_PLATFORM = (System.getProperty(TESTLINK_PLATFORM_KEY) != null ? System.getProperty(TESTLINK_PLATFORM_KEY) : hmLoadDataFromXml.get(TESTLINK_PLATFORM_KEY));
            TESTLINK_BUILD = (System.getProperty(TESTLINK_BUILD_KEY) != null ? System.getProperty(TESTLINK_BUILD_KEY) : hmLoadDataFromXml.get(TESTLINK_BUILD_KEY));
            TESTLINK_PREFIX = (System.getProperty(TESTLINK_PREFIX_KEY) != null ? System.getProperty(TESTLINK_PREFIX_KEY) : hmLoadDataFromXml.get(TESTLINK_PREFIX_KEY));

            PROTOCOL = (System.getProperty(PROTOCOL_KEY) != null ? System.getProperty(PROTOCOL_KEY) : hmLoadDataFromXml.get(PROTOCOL_KEY));
            SERVER = (System.getProperty(SERVER_KEY) != null ? System.getProperty(SERVER_KEY) : hmLoadDataFromXml.get(SERVER_KEY));
            PORT = (System.getProperty(PORT_KEY) != null ? System.getProperty(PORT_KEY) : hmLoadDataFromXml.get(PORT_KEY));
            DIRECTORY = (System.getProperty(DIRECTORY_KEY) != null ? System.getProperty(DIRECTORY_KEY) : hmLoadDataFromXml.get(DIRECTORY_KEY));
            PAGE = (System.getProperty(PAGE_KEY) != null ? System.getProperty(PAGE_KEY) : hmLoadDataFromXml.get(PAGE_KEY));

            TIMEOUT_IMPLICIT = Integer.parseInt(System.getProperty(TIMEOUT_IMPLICIT_KEY) != null ? System.getProperty(TIMEOUT_IMPLICIT_KEY) : hmLoadDataFromXml.get(TIMEOUT_IMPLICIT_KEY));
            TIMEOUT_SCRIPT = Integer.parseInt(System.getProperty(TIMEOUT_SCRIPT_KEY) != null ? System.getProperty(TIMEOUT_SCRIPT_KEY) : hmLoadDataFromXml.get(TIMEOUT_SCRIPT_KEY));
            TIMEOUT_PAGELOAD = Integer.parseInt(System.getProperty(TIMEOUT_PAGELOAD_KEY) != null ? System.getProperty(TIMEOUT_PAGELOAD_KEY) : hmLoadDataFromXml.get(TIMEOUT_PAGELOAD_KEY));
            TIMEOUT_ENV = Integer.parseInt(System.getProperty(TIMEOUT_ENV_KEY) != null ? System.getProperty(TIMEOUT_ENV_KEY) : hmLoadDataFromXml.get(TIMEOUT_ENV_KEY));

            BROWSER_NAME = (System.getProperty(BROWSER_NAME_KEY) != null ? System.getProperty(BROWSER_NAME_KEY) : hmLoadDataFromXml.get(BROWSER_NAME_KEY));
            DRIVER_TYPE = (System.getProperty(DRIVER_TYPE_KEY) != null ? System.getProperty(DRIVER_TYPE_KEY) : hmLoadDataFromXml.get(DRIVER_TYPE_KEY));
            DRIVER_LOCATION = (System.getProperty(DRIVER_LOCATION_KEY) != null ? System.getProperty(DRIVER_LOCATION_KEY) : hmLoadDataFromXml.get(DRIVER_LOCATION_KEY));
            setDriverLocation();

            CONTENT_MISSING_FF = (System.getProperty(CONTENT_MISSING_MSG_FF_KEY) != null ? System.getProperty(CONTENT_MISSING_MSG_FF_KEY) : hmLoadDataFromXml.get(CONTENT_MISSING_MSG_FF_KEY));
            CONTENT_MISSING_GC = (System.getProperty(CONTENT_MISSING_MSG_GC_KEY) != null ? System.getProperty(CONTENT_MISSING_MSG_GC_KEY) : hmLoadDataFromXml.get(CONTENT_MISSING_MSG_GC_KEY));
            CONTENT_MISSING_IE = (System.getProperty(CONTENT_MISSING_MSG_IE_KEY) != null ? System.getProperty(CONTENT_MISSING_MSG_IE_KEY) : hmLoadDataFromXml.get(CONTENT_MISSING_MSG_IE_KEY));
            TIMEOUT_FF = (System.getProperty(TIMEOUT_MSG_FF_KEY) != null ? System.getProperty(TIMEOUT_MSG_FF_KEY) : hmLoadDataFromXml.get(TIMEOUT_MSG_FF_KEY));
            TIMEOUT_GC = (System.getProperty(TIMEOUT_MSG_GC_KEY) != null ? System.getProperty(TIMEOUT_MSG_GC_KEY) : hmLoadDataFromXml.get(TIMEOUT_MSG_GC_KEY));
            TIMEOUT_IE = (System.getProperty(TIMEOUT_MSG_IE_KEY) != null ? System.getProperty(TIMEOUT_MSG_IE_KEY) : hmLoadDataFromXml.get(TIMEOUT_MSG_IE_KEY));

            GRID_MODE = (System.getProperty(GRID_MODE_KEY) != null ? System.getProperty(GRID_MODE_KEY) : hmLoadDataFromXml.get(GRID_MODE_KEY));
            OS_NAME = (System.getProperty(OS_NAME_KEY) != null ? System.getProperty(OS_NAME_KEY) : hmLoadDataFromXml.get(OS_NAME_KEY));

            DOWNLOAD_PATH = (System.getProperty(DOWNLOAD_PATH_KEY) != null ? System.getProperty(DOWNLOAD_PATH_KEY) : hmLoadDataFromXml.get(DOWNLOAD_PATH_KEY));
        }catch (Exception ex){
            LOGGER.error("Error ! Configuration not found - " + ex.getMessage());
            throw new FrameworkException("Configuration not found error on Class:" + Constant.class.getSimpleName() + " line #:" + Thread.currentThread().getStackTrace()[1].getLineNumber(), ex);
        }
    }

    /**
     * Set driver location absolute path
     */
    public static void setDriverLocation() {
        URL driverStream = null;
        driverStream = Constant.class.getClassLoader().getResource("drivers/");
        switch (BROWSER_NAME) {
            case "chrome":
                DRIVER_LOCATION = driverStream.getPath() + DRIVER_LOCATION;
                break;
            case "ie":
                DRIVER_LOCATION = driverStream.getPath() + DRIVER_LOCATION;
                break;
            case "firefox":
                DRIVER_LOCATION = DRIVER_LOCATION;
                break;
            case "edge":
                DRIVER_LOCATION = driverStream.getPath() + DRIVER_LOCATION;
                break;
        }
    }

}
