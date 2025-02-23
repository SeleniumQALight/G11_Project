package org.loginTest;


import org.baseTest.BaseTest;
import org.categories.SmokeTestFilter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;


public class LoginTestWithPageObject extends BaseTest {

    // Test case for
    @Test
    @Ignore // ignore this test instead of commenting it as we can forget it
    @Category(SmokeTestFilter.class)
    //LoginTestWithPageObject#T001_validLogin
    public void T001_validLogin() {
// chain method
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }

    @Test
    public void T002a_invalidLogin() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaato1");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456");
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignInVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkIfMessageInvalidLoginPasswordVisible();
    }

    //Test case for sign out
    @Test
    public void T002b_SignOut() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonAvatarVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonChatVisible();
        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostInVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonAvatarInVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonChatInVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkIsInputLoginVisible();
        pageProvider.getLoginPage().checkIsInputPasswordVisible();


    }

    @Test
    public void T4_ValidLoginUserLoggedInInNewTab() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().openNewTab();
        pageProvider.getHomePage().switchToNewTab(1);
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().switchToMainTab(0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().openNewTab();
        pageProvider.getHomePage().closeCurrentTab();
        pageProvider.getHomePage().switchToMainTab(0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();


    }

    @Test
    public void T5_RefreshPageValidLoginPasswordDisappear() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.getLoginPage().refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
    }
}
