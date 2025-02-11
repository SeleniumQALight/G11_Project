package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {

    String environment = System.getProperty("env", "aqa");
//    protected String baseUrl = "https://" + environment + "-complexapp.onrender.com";
    protected String baseUrl = ConfigProvider.configProperties.base_url()
        .replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }
    //перевірка чи поточний URL відповідає шаблону
    //https://aqa-complexapp.onrender.com/post/5f7b3b3b8f3b9d0017f3b3b3
    //regex for 5f7b3b3b8f3b9d0017f3b3b3 is [a-zA-Z0-9]{24} - 24 символи букви або цифри, можна не вказувати

    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

}
