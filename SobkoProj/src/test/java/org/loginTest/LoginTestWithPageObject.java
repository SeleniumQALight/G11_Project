package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void T0001_validLogin() {
        pageProvider.geLoginPage().openPage();
        pageProvider.geLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.geLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.geLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }
    @Test
    public void T0001_inValidLogin() {
        pageProvider.geLoginPage().openPage();
        pageProvider.geLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.geLoginPage().enterTextIntoInputPassword("123456qwerty1");
        pageProvider.geLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutNotVisible();
        pageProvider.getHomePage().checkIsButtonSignInVisible();
        pageProvider.getHomePage().checkIsWarningMessageVisible();

    }
}
