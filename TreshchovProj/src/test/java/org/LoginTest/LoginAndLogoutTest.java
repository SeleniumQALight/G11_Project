package org.LoginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginAndLogoutTest extends BaseTest {

    @Test
    public void HW4_validLoginAndLogout() {
        pageProvider.getLoginPage().openPage().
                enterTextIntoInputLogin(VALID_LOGIN).
                enterTextIntoInputPassword(VALID_PASSWORD).
                clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonChatVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSearchVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileVisible();

        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostNotVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonChatNotVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSearchNotVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileNotVisible();
    }
}
