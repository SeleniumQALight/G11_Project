package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {
    private Logger logger = Logger.getLogger(getClass());
    protected String baseUrl = "https://aqa-complexapp.onrender.com";
    String environment = System.getProperty("env", "aqa");
   // protected String baseUrl = "https://"+environment+"-complexapp.onrender.com";
    protected String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected" + "Expected URL: " + baseUrl + getRelativeUrl() + "Actual URL: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }

    public void switchToNewTab() {
        try {
            for (String winHandle : webDriver.getWindowHandles()) {
                webDriver.switchTo().window(winHandle);
            }
            logger.info("Switched to new tab");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void switchToFirstTab() {
        try {
            webDriver.switchTo().defaultContent();
            logger.info("Switched to first tab");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void closeCurrentTab() {
        try {
            webDriver.close();
            logger.info("New tab was closed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
}
