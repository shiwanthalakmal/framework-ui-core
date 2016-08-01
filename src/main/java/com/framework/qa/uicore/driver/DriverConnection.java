package com.framework.qa.uicore.driver;

import com.framework.qa.utils.exception.FrameworkException;
import com.framework.qa.uicore.util.Constant;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * DriverConnection.java -  Selenium web-drive related configurations
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_22_2016
 * @since 04/22/2016.
 */
public class DriverConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverConnection.class);
    public static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
    protected static RemoteWebDriver driver = null;
    protected static String strAppUrl;
    protected static DesiredCapabilities capability;

    /**
     * Get Currently Running Driver Connection
     *
     * @return WebDriver - Currently Running WebDriver Connection
     * @throws FrameworkException
     */
    public static RemoteWebDriver getDeriverInstance() throws FrameworkException {
        if (driver == null) {
            if (Constant.GRID_MODE.equals("ON")) {
                enableGridMode();
            } else {
                setWebDriverLocation();
                setDesiredCapabilities();
                setWebBrowserInstance();
            }
        }
        setWebDriverSettings();
        LOGGER.info("[NOTE]: Driver Has Been Initialized");
        return driver;
    }

    /**
     * Close Currently Running WebDriver Instance
     *
     * @throws FrameworkException
     */
    public static void closeDriver() throws FrameworkException {
        if (!Constant.BROWSER_NAME.equals("firefox")) {
            driver.close();
        }
        driver.quit();
        driver = null;
        LOGGER.info("[NOTE]: Close Driver Connection");
    }

    /**
     * Set WebDriver Instance From Out-Side
     *
     * @param driver
     */
    public static void setWebDeriverInstance(RemoteWebDriver driver) {
        DriverConnection.driver = driver;
        LOGGER.info("[NOTE]: Set External Driver Instance");
    }

    /**
     * Setup Basic WebDriver Browser Settings
     *
     * @throws FrameworkException
     */
    private static void setWebDriverSettings() throws FrameworkException {
        try {
            LOGGER.info("[NOTE]: Initiate " + Constant.BROWSER_NAME.toUpperCase() + " Driver");
            if (Constant.PORT != "" && Constant.PORT != null) {
                strAppUrl = Constant.PROTOCOL + "://" + Constant.SERVER + ":" + Constant.PORT + "/" + Constant.DIRECTORY + "/" + Constant.PAGE;
            } else {
                strAppUrl = Constant.PROTOCOL + "://" + Constant.SERVER;
            }
            driver.manage().timeouts().implicitlyWait(Constant.TIMEOUT_IMPLICIT, TimeUnit.MILLISECONDS);
            if (Constant.BROWSER_NAME.equals("edge")) {
                driver.navigate().to(strAppUrl);
                driver.manage().deleteAllCookies();
            } else {
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.navigate().to(strAppUrl);
            }
            LOGGER.info("[NOTE]: Browser Loaded And Navigated To : [" + strAppUrl + " ]");
        } catch (Throwable e) {
            throw new FrameworkException(e.getCause().toString());
        }
    }

    /**
     * Set Drivers Location System Properties
     *
     * @throws FrameworkException
     */
    private static void setWebDriverLocation() throws FrameworkException {
        try {
            System.setProperty(Constant.DRIVER_TYPE, Constant.DRIVER_LOCATION);
        } catch (Throwable e) {
            throw new FrameworkException(e.getCause().toString());
        }
    }

    /**
     * Initiate Specific Browser Instance
     *
     * @throws FrameworkException
     */
    private static void setWebBrowserInstance() throws FrameworkException {
        switch (Constant.BROWSER_NAME) {
            case "chrome":
                try {
                    driver = new ChromeDriver(capability);
                    break;
                } catch (Throwable e) {
                    throw new FrameworkException(e.getCause().toString());
                }
            case "firefox":
                try {
                    driver = new FirefoxDriver(capability);
                    break;
                } catch (Throwable e) {
                    throw new FrameworkException(e.getCause().toString());
                }
            case "ie":
                try {
                    driver = new InternetExplorerDriver(capability);
                    break;
                } catch (Throwable e) {
                    throw new FrameworkException(e.getCause().toString());
                }
            case "edge":
                try {
                    driver = new EdgeDriver(capability);
                    break;
                } catch (Throwable e) {
                    throw new FrameworkException(e.getCause().toString());
                }
        }
    }

    /**
     * Enable Browsers Grid Execution Mode
     *
     * @throws FrameworkException
     */
    private static void enableGridMode() throws FrameworkException {
        try {
            capability.setPlatform(Platform.WIN8_1);
            switch (Constant.BROWSER_NAME) {
                case "chrome":
                    try {
                        capability.setBrowserName("chrome");
                        break;
                    } catch (Throwable e) {
                        throw new FrameworkException(e.getCause().toString());
                    }
                case "firefox":
                    try {
                        capability.setBrowserName("firefox");
                        break;
                    } catch (Throwable e) {
                        throw new FrameworkException(e.getCause().toString());
                    }
                case "ie":
                    try {
                        capability.setBrowserName("ie");
                        break;
                    } catch (Throwable e) {
                        throw new FrameworkException(e.getCause().toString());
                    }
                case "edge":
                    try {
                        capability.setBrowserName("edge");
                        break;
                    } catch (Throwable e) {
                        throw new FrameworkException(e.getCause().toString());
                    }
            }
            URL url = new URL("http://127.0.0.1:5557/wd/hub");
            driver = new RemoteWebDriver(url, capability);
            LOGGER.info("[NOTE]: Enabled Grid Execution Mode : [" + url.toString() + " ]");
        } catch (Throwable e) {
            throw new FrameworkException(e.getCause().toString());
        }
    }

    /**
     * Apply DesiredCapabilities For Specific Browsers
     *
     * @throws FrameworkException
     */
    private static void setDesiredCapabilities() throws FrameworkException {
        capability = new DesiredCapabilities();
        switch (Constant.BROWSER_NAME) {
            case "chrome":
                try {
                    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                    chromePrefs.put("profile.default_content_settings.popups", 0);
                    chromePrefs.put("download.default_directory", Constant.DOWNLOAD_PATH);
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", chromePrefs);
                    capability = DesiredCapabilities.chrome();
                    capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    capability.setCapability(ChromeOptions.CAPABILITY, options);
                    break;
                } catch (Throwable e) {
                    throw new FrameworkException(e.getCause().toString());
                }
            case "firefox":
                try {
                    ProfilesIni profileIni = new ProfilesIni();
                    FirefoxProfile profile = profileIni.getProfile("AutoProfile");
                    profile.setAcceptUntrustedCertificates(true);
                    profile.setAssumeUntrustedCertificateIssuer(false);
                    profile.setEnableNativeEvents(true);

                    profile.setPreference("browser.download.folderList", 2);
                    profile.setPreference("browser.download.dir", Constant.DOWNLOAD_PATH);
                    profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
                    profile.setPreference("browser.download.manager.showWhenStarting", false);
                    profile.setPreference("browser.download.manager.focusWhenStarting", false);
                    profile.setPreference("browser.download.useDownloadDir", true);
                    profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                    profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                    profile.setPreference("browser.download.manager.closeWhenDone", true);
                    profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                    profile.setPreference("browser.download.manager.useWindow", false);
                    profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
                    profile.setPreference("pdfjs.disabled", true);

                    capability = DesiredCapabilities.firefox();
                    capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    capability.setCapability(FirefoxDriver.PROFILE, profile);
                    break;
                } catch (Throwable e) {
                    throw new FrameworkException(e.getCause().toString());
                }
            case "ie":
                try {
                    capability = DesiredCapabilities.internetExplorer();
                    capability.setCapability("ignoreZoomSetting", true);
                    capability.setPlatform(Platform.WINDOWS);
                    capability.setVersion("ANY");
                    capability.setCapability("nativeEvents",false);
                    capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    break;
                } catch (Throwable e) {
                    throw new FrameworkException(e.getCause().toString());
                }
            case "edge":
                try {
                    capability = DesiredCapabilities.edge();
                    break;
                } catch (Throwable e) {
                    throw new FrameworkException(e.getCause().toString());
                }
        }
    }
}
