package com.framework.qa.uicore.actions;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Common.java - Common methods implementation for web portal
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_22_2016
 * @since 04/22/2016.
 */
public class Common {
    private static WebElement element;
    private static List<WebElement> lstWebElements;

    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class);

    /**
     * Make an explicit delay
     *
     * @param time
     */
    public static void waitFor(long time) {
        try {
            Thread.sleep(time);
            LOGGER.info("[NOTE]: Explicit Wait For: "+ time);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Load a new web page in the current browser window. This is done using an HTTP GET operation,
     * and the method will block until the load is complete.
     *
     * @param driver
     * @param url    The URL to load. It is best to use a fully qualified URL
     */
    public static void pageLoad(RemoteWebDriver driver, String url) {
        driver.get(url);
        LOGGER.info("[NOTE]: Page Load: "+url);
    }

    /**
     * Get a string representing the current URL that the browser is looking at.
     *
     * @param driver
     * @return The URL of the page currently loaded in the browser
     */
    public static String getCurrentURL(RemoteWebDriver driver) {
        LOGGER.info("[NOTE]: Return Existing Page Url");
        return driver.getCurrentUrl();
    }

    /**
     * The title of the current page.
     *
     * @param driver
     * @return The title of the current page, with leading and trailing whitespace stripped, or null
     * if one is not already set
     */
    public static String getPageTitle(RemoteWebDriver driver) {
        LOGGER.info("[NOTE]: Return Page Title");
        return driver.getTitle();
    }

    /**
     * Get the source of the last loaded page.
     *
     * @param driver
     * @return The source of the current page
     */
    public static String getPageSource(RemoteWebDriver driver) {
        LOGGER.info("[NOTE]: Return Page Source Code");
        return driver.getPageSource();
    }

    /**
     * Verify The Current Page Loading Status
     *
     * @param driver
     * @return Page load status as boolean
     */
    public static boolean isPageLoaded(RemoteWebDriver driver) {
        String state = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
        if (state.equalsIgnoreCase("complete")) {
            LOGGER.info("[NOTE]: Page Load Successful");
            return true;
        }
        LOGGER.warn("[NOTE]: Page Load Un-Successful");
        return false;
    }

    /**
     * Implicit Wait For Until Page Load
     *
     * @param timeout
     * @param driver
     */
    public static void waitForPageToLoad(long timeout, RemoteWebDriver driver) {
        long waitingFor = 0;
        while (!isPageLoaded(driver)) {
            waitFor(500);
            waitingFor += 500;
            if (waitingFor >= timeout) {
                LOGGER.warn("[NOTE]: Page Load Wait Has Been Time Out");
            }
        }
        LOGGER.info("[NOTE]: Wait Until Page Load");
    }

    /**
     * Validate if an element is present in the current page
     *
     * @param driver current instance
     * @param by     element name
     * @return list of web elements
     * @throws ElementNotVisibleException
     * @throws NoSuchElementException
     * @throws IOException
     */
    public static WebElement isElementPresent(RemoteWebDriver driver, By by) throws ElementNotVisibleException, NoSuchElementException, IOException {
        lstWebElements = driver.findElements(by);
        if (lstWebElements.size() == 0) {
            throw new NoSuchElementException("Element is Not present : " + by + " : ");
        }
        LOGGER.info("[NOTE]: Element Is Present");
        return lstWebElements.get(0);
    }

    /**
     * Find if dynamic element has been loaded
     *
     * @param driver     current driver instance
     * @param by         element name
     * @param intTimeOut wait time till page loads
     * @return element
     * @throws SessionNotCreatedException
     * @throws IOException
     */
    public static WebElement findDynamicElement(RemoteWebDriver driver, By by, int intTimeOut) throws SessionNotCreatedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, intTimeOut);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LOGGER.info("[NOTE]: Select Dynamic Element");
        return element;
    }

    /**
     * Find if dynamic element has been loaded with an effect of attribute change
     *
     * @param driver     current driver instance
     * @param ele        element name
     * @param intTimeOut wait time till element loads
     * @return element
     * @throws SessionNotCreatedException
     * @throws IOException
     */
    public static WebElement waitForElementVisibleWithEffectOfAttrChange(RemoteWebDriver driver, final WebElement ele, final String attr, final String expectedVal, int intTimeOut) throws SessionNotCreatedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, intTimeOut);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                String val = ele.getAttribute(attr);
                if (val.equals(expectedVal))
                    return true;
                else
                    return false;
            }
        });
        return element;
    }

    /**
     * Define wait time till page loads
     *
     * @param milliSec time to load
     * @throws InterruptedException
     */
    public static void waitFor(Integer milliSec) throws InterruptedException {
        Thread.sleep(milliSec);
        LOGGER.info("[NOTE]: Explicit Wait: "+milliSec);
    }

    /**
     * Select random element from list of elements generated
     *
     * @param list of web elements
     * @return selected random element
     */
    public static WebElement selectRandomElementFromList(List<WebElement> list) {
        Random random = new Random();
        element = list.get(random.nextInt(list.size()));
        LOGGER.info("[NOTE]: Select Random Element From List");
        return element;
    }

    /**
     * Generate list of all displayed elements found in the current web page
     *
     * @param driver  current instance
     * @param locator element name
     * @return list of elements successfully displayed
     * @throws SessionNotCreatedException
     * @throws IOException
     */
    public static List<WebElement> findDisplayedElements(RemoteWebDriver driver, By locator) throws SessionNotCreatedException, IOException {
        List<WebElement> lstWebElementOptions = driver.findElements(locator);
        List<WebElement> lstWebDisplayedOptions = new ArrayList<WebElement>();
        for (WebElement option : lstWebElementOptions) {
            if (option.isDisplayed()) {
                lstWebDisplayedOptions.add(option);
            }
        }
        LOGGER.info("[NOTE]: Found Displayed Elements");
        return lstWebDisplayedOptions;
    }

    /**
     * Navigate To The Given URL And Wait For the Page Load
     *
     * @param driver
     * @param url
     */
    public static void navigateTo(RemoteWebDriver driver, String url) {
        driver.navigate().to(url);
        Common.waitForPageToLoad(5000, driver);
        LOGGER.info("[NOTE]: Navigate To: "+url);
    }

    /**
     * Navigate to previous page
     *
     * @param driver
     */
    public static void navigateBack(RemoteWebDriver driver) {
        driver.navigate().back();
        LOGGER.info("[NOTE]: Navigate To Back");
    }

    /**
     * Switch browser tabs
     *
     * @param driver
     * @return RemoteWebDriver
     */
    public static RemoteWebDriver switchDrivers(RemoteWebDriver driver, int index) {
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(index));
        LOGGER.info("[NOTE]: Switch Window: "+index);
        return driver;
    }

    /**
     * Perform page refresh
     *
     * @param driver
     */
    public static void pageRefresh(RemoteWebDriver driver) {
        driver.navigate().refresh();
        LOGGER.info("[NOTE]: Refresh Page");
    }
}
