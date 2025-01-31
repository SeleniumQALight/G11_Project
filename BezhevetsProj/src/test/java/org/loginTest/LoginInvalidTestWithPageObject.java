package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginInvalidTestWithPageObject extends BaseTest {
    @Test
    public void T0004_invalidLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin("invalidLogin")
                .enterTextIntoInputPassword("invalidPassword")
                .clickOnButtonSignIn();

        pageProvider.getLoginPage().getHeaderElement().checkIsButtonSignOutNotVisible().checkIsButtonSignInVisible().isAlertInvalidLoginOrPasswordDisplayed();
    }
}
