package org.logoutTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LogoutTestWithPageObject extends BaseTest {
    @Test
    public void TC0002_validLogout() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderElement()
                .checkIsSearchButtonVisible()
                .checkIsChatButtonVisible()
                .checkIsMyProfileButtonVisible()
                .checkIsButtonCreatePostVisible()
                .checkIsButtonSignOutVisible();

        pageProvider.getHomePage()
                .getHeaderElement()
                .clickOnButtonSignOut()
                .checkIsRedirectToLoginPage()
                .checkIsButtonSignInVisible()
                .checkIsUsernameFieldVisible()
                .checkIsPasswordFieldVisible();

        pageProvider.getHomePage()
                .getHeaderElement()
                .checkIsSearchButtonNotVisible()
                .checkIsChatButtonNotVisible()
                .checkIsMyProfileButtonNotVisible()
                .checkIsButtonCreatePostNotVisible()
                .checkIsButtonSignOutNotVisible();
    }

}
