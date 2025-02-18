package org.loginTest;


import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutVisible()
                .checkIsButtonCreatePostVisible();

        pageProvider.getLoginPage()
                .checkIsInputUserNameRegistrationFormNotVisible()
                .checkIsInputEmailInRegistrationFormNotVisible();
    }

    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(INVALID_LOGIN)
                .enterTextIntoInputPassword(INVALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getLoginPage().checkIsInvalidUsernamePasswordMessageVisible()
                .checkIsButtonSignInVisible();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSingOutNotVisible();

    }

    @Test
    public void T0004_SingOut() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutVisible()
                .checkIsButtonCreatePostVisible()
                .checkIsButtonMyProfileVisible()
                .checkIsButtonChatVisible()
                .checkIsSearchVisible()
                .clickOnButtonSingOut();

        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutNotVisible()
                .checkIsButtonCreatePostNotVisible()
                .checkIsButtonMyProfileNotVisible()
                .checkIsButtonChatNotVisible()
                .checkIsSearchNotVisible();

        pageProvider.getLoginPage()
                .checkIsButtonSignInVisible()
                .checkIsInputUserNameFieldVisible()
                .checkIsInputPasswordFieldVisible();

    }


}
