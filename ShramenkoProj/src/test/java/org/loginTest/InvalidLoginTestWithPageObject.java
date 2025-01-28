package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.INVALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class InvalidLoginTestWithPageObject extends BaseTest {
    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(INVALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSighIn();

        //а тепер обов'язкова перевірка, чи все вийшло

        pageProvider.getLoginPage().checkIsButtonSighInVisible();
        pageProvider.getLoginPage().checkIsAlertIncorrectLoginPasswordVisible();
        pageProvider.getHomePage().checkIsButtonSighOutNotVisible();

    }


}
