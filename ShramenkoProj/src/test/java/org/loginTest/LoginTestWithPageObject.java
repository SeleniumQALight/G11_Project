package org.loginTest;

import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class)
    public void T0001_validLogin() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginWithValidCred();

        //а тепер обов'язкова перевірка, чи все вийшло
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();
//        pageProvider.getLoginPage().checkIsButtonSighInNotVisible();
//        pageProvider.getLoginPage().checkIsInputLoginAndPasswordNotVisible();

    }

}
