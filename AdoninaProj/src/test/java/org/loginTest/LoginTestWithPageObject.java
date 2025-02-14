package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;
import static org.data.TestData.INVALID_LOGIN;
import static org.data.TestData.INVALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
  @Test
  public void T0001_validLogin() {
    pageProvider.getLoginPage()
            .openPage()
            .enterTextIntoInputLogin(VALID_LOGIN)
            .enterTextIntoInputPassw0rd(VALID_PASSWORD)
            .clickOnButtonSignIn();

    pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible()
            .checkIsButtonCreatePostVisible();

    pageProvider.getLoginPage()
            .checkIsInputUsernameNotVisible()
            .checkIsInputPasswordNotVisible()
    ;
  }

  @Test
  public void T0002_invalidLogin() {
    pageProvider.getLoginPage()
            .openPage()
            .enterTextIntoInputLogin(INVALID_LOGIN)
            .enterTextIntoInputPassw0rd(INVALID_PASSWORD)
            .clickOnButtonSignIn()
            .checkIsErrorMessageDisplayed("Invalid username/password.")
    ;
    pageProvider.getLoginPage().checkIsButtonSignInVisible();
    pageProvider.getHomePage().getHeaderElement()
            .checkIsSignOutButtonNotVisible()
    ;
  }

  @Test
  public void T0004_signOut() {
    pageProvider.getLoginPage()
            .openLoginPageAndFillLoginFormWithValidCred()
    ;
    pageProvider.getHomePage().getHeaderElement().checkAllElementsInHeaderVisible()
            .clickOnButtonSignOut()
    ;
    pageProvider.getHomePage().getHeaderElement()
            .checkElementsInHeaderNotVisible();
    pageProvider.getLoginPage()
            .checkAllElementsOnLoginPageInHeaderVisible()
    ;
  }
}
