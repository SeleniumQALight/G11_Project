package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

import java.util.ArrayList;

abstract public class ParentPage extends CommonActionsWithElements{

    String environment = System.getProperty("env", "aqa");

    //protected String baseUrl = "https://" + environment + "-complexapp.onrender.com";
protected String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    //method to check url

    protected void checkURL() {
        Assert.assertEquals("URL is not expected"
                , baseUrl + this.getRelativeUrl()
                , webDriver.getCurrentUrl());
    }
    // метод по перевірці чи відкрита потрібна сторінка по патерну
//https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
// regex for 64d21e84903640003414c338
// [a-zA-Z0-9]{24} // 24 символи з алфавіту або цифри

    protected void checkUrlWithPattern(){
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

    public void switchToNewTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabIndex));
    }

    public void switchToMainTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabIndex));
    }

}
