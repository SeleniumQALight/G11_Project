package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class InvalidLoginTestWithPageObject extends BaseTest {
    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginWithInvalidCred();

        //а тепер обов'язкова перевірка, чи все вийшло

        pageProvider.getLoginPage().checkIsButtonSighInVisible();
        pageProvider.getLoginPage().checkIsAlertIncorrectLoginPasswordVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutNotVisible();
    }


}
