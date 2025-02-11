package org.loginTest;


import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;


public class LoginTestWithPageObject extends BaseTest {

    // Test case for
    @Test
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
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutInvisible();
        pageProvider.getLoginPage().checkIfMessageInvalidLoginPasswordVisible();
    }

    //Test case for sign out
    @Test
    public void T002b_SignOut() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignInVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonAvatarVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonChatVisible();
        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutInvisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostInVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonAvatarInVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonChatInVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignInVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsInputLoginVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsInputPasswordVisible();




    }
}
