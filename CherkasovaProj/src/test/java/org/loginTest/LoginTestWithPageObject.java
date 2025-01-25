package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void T001_validLogin(){
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
//        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
//        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
//        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutVisible();

    }

    @Test
    public void T002_invalidLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(INVALID_LOGIN)
                .enterTextIntoInputPassword(INVALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkIsButtonSignInVisible();
        pageProvider.getLoginPage().checkIsSussesMessageDisplayed();
        pageProvider.getLoginPage().checkIsRedirectToLoginPage();


    }
}
