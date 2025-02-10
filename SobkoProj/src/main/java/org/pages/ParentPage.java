package org.pages;

import org.openqa.selenium.WebDriver;
import org.junit.Assert;

abstract public class ParentPage extends CommonActionsWithElements {
    protected String baseUrl = "https://aqa-complexapp.onrender.com";

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

