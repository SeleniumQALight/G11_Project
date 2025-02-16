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
    public void T0006_StayLoggedInNewTab() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutVisible();

        pageProvider.getHomePage().openNewTab();
        pageProvider.getLoginPage().switchToNewTab();
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutVisible();

        pageProvider.getHomePage().switchToMainTab();
        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutVisible();

        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getHomePage().closeCurrentTab();
        pageProvider.getHomePage().switchToMainTab();
        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutVisible();
    }

    @Test
    public void T007_inputDataDisappearsAfterRefreshing () {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .refreshPage();
        pageProvider.getLoginPage()
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutNotVisible();
    }

}
