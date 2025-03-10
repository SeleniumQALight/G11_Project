package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

import java.util.ArrayList;

abstract public class ParentPage extends CommonActionsWithElements {
    String environment = System.getProperty("env", "aqa");

//    protected String baseUrl = "https://" + environment + "-complexapp.onrender.com";

    protected String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    private Logger logger = Logger.getLogger(getClass());

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
    }

    //method for checking URL with pattern
    //for example, if we have a page with a dynamic URL
    //like /profile/[a-zA-Z0-9]*
    //Example:
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

    // open new tab using JS
    public void openNewTab() {
        try {
            ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // switch to new tab
    public void switchToTab(int numberOfTab) {
        try {
            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(numberOfTab));
            logger.info("Switched to " + numberOfTab + " tab");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // Close current tab
    public void closeCurrentTab() {
        try {
            webDriver.close();
            logger.info("Current tab was closed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // method for page refresh
    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


}
