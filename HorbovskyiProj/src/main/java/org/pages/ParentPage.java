package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

import java.util.ArrayList;

abstract public class ParentPage extends CommonActionsWithElements {
    static String environment = System.getProperty("env", "aqa");

    private Logger logger = Logger.getLogger(getClass());

//    protected String baseUrl = "https://" + environment + "-complexapp.onrender.com";
    public static String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    // method for checking URL with pattern
    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

    public void switchToTab(int numberOfTab) {
        try {
            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(numberOfTab));
            logger.info("Switched to new tab");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public ParentPage refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page was refreshed");
        return this;
    }

}
