package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SighOutTestWithPageObject extends BaseTest {

    @Test
    public void T0004_SighOut() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginWithValidCred()
                .checkIsRedirectToHomePage().getHeaderElement()
                .checkIsButtonSearchVisible()
                .checkIsButtonChatVisible()
                .checkIsAvatarVisible()
                .checkIsButtonCreatePostVisible()
                .checkIsButtonSighOutVisible()
                .clickOnButtonSignOut()
                .checkIsRedirectToLoginPage()
                .checkIsButtonSighInVisible()
                .checkIsInputLoginAndPasswordVisible()
        ;

        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSearchNotVisible()
                .checkIsButtonChatNotVisible()
                .checkIsAvatarNotVisible()
                .checkIsButtonCreatePostNotVisible()
                .checkIsButtonSighOutNotVisible()
        ;
    }

}
