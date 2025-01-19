package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class InvalidLoginTestWithPageObject extends BaseTest {

    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto1");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutNotVisible();
        pageProvider.getHomePage().checkIsButtonSignInVisible();
        pageProvider.getHomePage().checkIsAllertVisible();
    }
}
