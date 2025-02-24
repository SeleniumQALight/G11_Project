package org.loginTest;


import org.baseTest.BaseTest;
import org.categiries.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class)
//    LoginTestWithPageObject#T0001_validLogin
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
        pageProvider.getLoginPage().switchToNewTab(1);
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutVisible();

        pageProvider.getHomePage().switchToMainTab();
        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutVisible();

        pageProvider.getHomePage().switchToNewTab(1);
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
