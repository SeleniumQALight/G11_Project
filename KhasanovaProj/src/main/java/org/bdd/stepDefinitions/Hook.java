package org.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.api.ApiHelper;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class Hook {
    WebDriverHelper webDriverHelper;
    ApiHelper apiHelper = new ApiHelper();

    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper; //за допомогою пікоконтейнеру ми можемо передати об'єкт класу WebDriverHelper
        //перед створенням об'єкта він дивиться, чи є все що потрібно для створення об'єкта в піконтейнері
        //якщо є, то створюється, якщо немає, то створюється все що потрібно для створення об'єкта
    }

    @Before(order = 10) //цифра вказує порядок виконання хуків, запускаються від найменшого до найбільшого
    public void setUp() {
//        webDriverHelper = new WebDriverHelper();
    }

    @After(order = 15) //цифра вказує порядок виконання хуків, запускаються від найбільшого до найменшого
    public void tearDown() {
        webDriverHelper.quiteDriver();
    }

    @Before(value = "@deletePostsTillPresentForDefaultUser", order = 50)
    @After(value = "@deletePostsTillPresentForDefaultUser", order = 50)
    public void deletePostsTillPresent(){
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API,
                apiHelper.getToken());
    }
}

