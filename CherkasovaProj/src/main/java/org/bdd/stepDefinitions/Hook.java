package org.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.api.ApiHelper;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class Hook { //Before and After hooks
    WebDriverHelper webDriverHelper;
    ApiHelper apiHelper = new ApiHelper();

    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }

    @Before(order = 10)
    public void setup() {
//        webDriverHelper = new WebDriverHelper();

    }

    @After(order = 15)
    public void tearDown() {
        webDriverHelper.quitDriver();
    }

    @Before(value = "@deletePostsTillPresentForDefaultUser", order = 50)
    @After(value = "@deletePostsTillPresentForDefaultUser", order = 50)
    public void deletePostsTillPresent() {
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API,
                apiHelper.getToken());
    }
}
