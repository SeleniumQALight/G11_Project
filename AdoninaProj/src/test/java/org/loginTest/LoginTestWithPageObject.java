package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
  @Test
  public void T0001_validLogin() {
    pageProvider.getLoginPage()
            .openPage()
            .enterTextIntoInputLogin(VALID_LOGIN)
            .enterTextIntoInputPassw0rd(VALID_PASSWORD)
            .clickOnButtonSignIn();

    pageProvider.getHomePage().checkIsButtonSignOutVisible()
            .checkIsButtonCreatePostVisible()
            .checkIsInputUsernameNotVisible()
            .checkIsInputPasswordNotVisible()
    ;
  }

  @Test
  public void T0002_invalidLogin() {
    pageProvider.getLoginPage()
            .openPage()
            .enterTextIntoInputLogin("notValidLogin")
            .enterTextIntoInputPassw0rd("notValidPassword")
            .clickOnButtonSignIn()
            .checkIsErrorMessageDisplayed("Invalid username/password.")
            .checkIsSignOutButtonNotVisible()
            .checkIsButtonSignInVisible()
    ;
  }
}
