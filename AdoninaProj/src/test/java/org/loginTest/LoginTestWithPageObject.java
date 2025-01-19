package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
  @Test
  public void T0001_validLogin() {
    pageProvider.getLoginPage().openPage();
    pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
    pageProvider.getLoginPage().enterTextIntoInputPassw0rd("123456qwerty");
    pageProvider.getLoginPage().clickOnButtonSignIn();

    pageProvider.getHomePage().checkIsButtonSignOutVisible();

  }

  @Test
  public void T0002_inValidLogin() {
    pageProvider.getLoginPage().openPage();
    pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto1");
    pageProvider.getLoginPage().enterTextIntoInputPassw0rd("123456qwerty");
    pageProvider.getLoginPage().clickOnButtonSignIn();

    pageProvider.getLoginPage().checkIsButtonSignOutNoVisible();
    pageProvider.getLoginPage().checkIsButtonSignInVisible();
    pageProvider.getLoginPage().checkIsInvalidLoginMassageVisible();

  }
}
