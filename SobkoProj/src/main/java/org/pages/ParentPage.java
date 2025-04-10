package org.pages;

import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import org.utils.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {
    static String environment = System.getProperty("env","aqa");
    //protected String baseUrl = "https://"+environment+"-complexapp.onrender.com";
    public static String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);
    public ParentPage(WebDriver webdriver) {
        super(webdriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected", baseUrl + getRelativeUrl(), webdriver.getCurrentUrl());
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webdriver.getCurrentUrl(),
                webdriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }
}

