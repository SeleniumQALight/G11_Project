package org.loginTest;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;
import static org.pages.elements.HeaderForLoggedInUserElement.buttonSearchLocator;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage()
                .checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage()
                .checkIsInputLoginOrPasswordNotVisible()
        ;
    }

    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(TestData.INVALID_LOGIN)
                .enterTextIntoInputPassword(TestData.INVALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getLoginPage().checkIsButtonSignInVisible();
        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSignOutNotVisible()
                .checkIsInvalidLoginOrPasswordMessageVisible();

    }

    @Test
    public void T0003_signOut() {
        //sign in
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();

// check if elements are visible:
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsSearchButtonVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsChatButtonVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsMyProfileButtonVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsCreatePostButtonVisible();
// sign out
        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();
        // check if elements are not visible:
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsSearchButtonNotVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsChatButtonNotVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsMyProfileButtonNotVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsCreatePostButtonNotVisible();
        // check if elements are visible:
        pageProvider.getLoginPage().checkIsButtonSignInVisible()
                .checkIsLoginInputVisible()
                .checkIsPasswordInputVisible();
    }
}
