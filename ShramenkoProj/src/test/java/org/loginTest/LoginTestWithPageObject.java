package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginWithValidCred();

        //а тепер обов'язкова перевірка, чи все вийшло
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();
        pageProvider.getLoginPage().checkIsButtonSighInNotVisible();
        pageProvider.getLoginPage().checkIsInputLoginAndPasswordNotVisible();

    }

}
