package org.pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.utils.ConfigProvider;

import java.util.ArrayList;

abstract public class ParantPage extends CommonActionsWithElements {
    private Logger logger = Logger.getLogger(getClass());

    String environment = System.getProperty("env", "aqa");
//    protected String baseUrl = "https://"+environment+"-complexapp.onrender.com";
    protected String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParantPage(WebDriver webDriver) {
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
    protected void checkUrlWithPattern(){
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }
    public void pressTabKey(int times) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(Keys.TAB).perform();
        }
        logger.info("Pressed Tab key " + times + " times");
    }
    public void enterTextIntoField(String text) {
        Actions actions = new Actions(webDriver);
        actions.sendKeys(text).perform();
        logger.info("Entered text: " + text);
    }
    public void refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page was refreshed");
    }
    public void pressEnter() {
        Actions actions = new Actions(webDriver);
        actions.sendKeys(Keys.ENTER).perform();
        logger.info("Pressed Enter key");
    }
    public void openNewTab() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("window.open()");
        logger.info("Opened a new tab");
    }
    public ArrayList switchToTab(int tabNumber) {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabNumber));
        logger.info("Switched to tab number " + tabNumber);
        return tabs;
    }
    public void closeCurrentTab() {
        WebDriver driver = webDriver;
        driver.close();
        logger.info("Closed the current tab");
    }


}
