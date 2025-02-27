package org.loginTest;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.*;

@Epic("Allure examples")
@Feature("Junit 4 support")

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class)

    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")

    public void T0001_validLogin() {
        pageProvider.getLoginPage()
//                .openLoginPageAndFillLoginWithValidCred();
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD+1)
                .clickOnButtonSighIn();

        //а тепер обов'язкова перевірка, чи все вийшло
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();
//        pageProvider.getLoginPage().checkIsButtonSighInNotVisible();
//        pageProvider.getLoginPage().checkIsInputLoginAndPasswordNotVisible();

    }

}
