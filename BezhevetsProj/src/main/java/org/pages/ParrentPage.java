package org.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

import java.util.ArrayList;

abstract public class ParrentPage extends CommonActionsWithElements {
    static String environment = System.getProperty("env", "aqa");
//    protected String baseUrl = "https://" + environment + "-complexapp.onrender.com";
    public static String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParrentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

    public void openNewTab() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.open('about:blank','_blank');");
    }

    // Метод для отримання списку вкладок
    public ArrayList<String> getWindowHandles() {
        return new ArrayList<>(webDriver.getWindowHandles());
    }

    public void switchToTab(int tabIndex) {
        ArrayList<String> tabs = getWindowHandles();
        if (tabIndex >= 0 && tabIndex < tabs.size()) {
            webDriver.switchTo().window(tabs.get(tabIndex));
        } else {
            throw new IllegalArgumentException("Invalid tab index: " + tabIndex);
        }
    }

    public void closeCurrentTab() {
        webDriver.close();
    }

    public void switchToMainTab() {
        switchToTab(0);
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
    }
}
