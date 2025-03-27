package org.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before; //обов'язково CUCUMBER!!!!
import org.api.ApiHelper;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class Hook {
    //клас для before and after секцій

    WebDriverHelper webDriverHelper;
    ApiHelper apiHelper = new ApiHelper();

    public Hook (WebDriverHelper webDriverHelper){
        this.webDriverHelper = webDriverHelper;
    }

    @Before(order = 10) //order це порядок виконання before секцій, від меншої до більшої цифри
    public void setUp() {
    //    webDriverHelper = new WebDriverHelper();
        //    так не робити, тому що ми не зможемо достукатися до нього

    }

    @After(order = 15) //тут перша запуститься 50, а потім в сторону меншої, тобто наступна це 10 наприклад
    public void tearDown() {
        webDriverHelper.quiteDriver();
    }


    @Before(value = "@deletePostsTillPresentForDefaultUser", order = 50) //мітка для якого теста це використовувати
    @After(value = "@deletePostsTillPresentForDefaultUser", order = 50) //запускаємо і до теста і після
    public void deletePostsTillPresent(){
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, apiHelper.getToken());
    }


}
