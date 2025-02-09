package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract public class ParentPage extends CommonActionsWithElements {
    protected String baseUrl = "https://aqa-complexapp.onrender.com";

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
}
