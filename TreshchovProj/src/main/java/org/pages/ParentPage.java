package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {
    Logger logger = Logger.getLogger(getClass());

    String environment = System.getProperty("env", "aqa");
    // protected String baseUrl = "https://"+environment+"-complexapp.onrender.com";
    protected String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("URL is not expected",
                baseUrl +  getRelativeUrl(),
                webDriver.getCurrentUrl());
    }

    //check url with pattern
    //https://aqa-complexapp.onrender.com/post/5f7b3b3b8f4b4d0017f2b3b3
    //regex for 5f7b3b3b8f4b4d0017f2b3b3
    //[a-zA-Z0-9]*
    protected void checkUrlWithPattern(){
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl +  getRelativeUrl()));
    }

    protected void switchToTab(int tabNumber) {
        try {
            webDriver.switchTo().window((String) webDriver.getWindowHandles().toArray()[tabNumber]);
            logger.info("Switched to tab " + tabNumber);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void closeTab(int tabNumber) {
        try {
            webDriver.switchTo().window((String) webDriver.getWindowHandles().toArray()[tabNumber]);
            webDriver.close();
            logger.info("Tab with index " + tabNumber + " was closed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

}
