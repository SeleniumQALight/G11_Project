package org.loginTest;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(TestData.INVALID_LOGIN)
                .enterTextIntoInputPassword(TestData.INVALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getLoginPage().checkIsButtonSignInVisible()
                .checkIsButtonSignOutNotVisible()
                .checkIsInvalidLoginOrPasswordMessageVisible()
        ;
    }
//
//    @Test
//    public void T0001_validLogin() {
//        pageProvider.getLoginPage().openPage();
//        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
//        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
//        pageProvider.getLoginPage().clickOnButtonSignIn();
//        checkIsButtonSignOutVisible();
//    }

    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().checkIsButtonCreatePostVisible();
        pageProvider.getHomePage().checkIsInputLoginOrPasswordNotVisible();
    }
}
