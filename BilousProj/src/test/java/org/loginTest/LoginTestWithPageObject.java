package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoPassword(VALID_PASSWORD)
                .clickInButtomSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutVisible();

    }
    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin("invalidLogin")
                .enterTextIntoPassword("0000000000")
                .clickInButtomSignIn();

        pageProvider.getLoginPage().checkIsButtonSignInVisible();
        pageProvider.getLoginPage().checkIsWarningMessageDisplayed();
        pageProvider.getHomePage().checkIsButtonSignOutInvisible();

        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred();
        pageProvider.getHomePage().checkIsButtonCreatePostIsVisible();
        pageProvider.getLoginPage().isLoginFieldInVisible();
        pageProvider.getLoginPage().isPasswordFieldInVisible();

    }
}
