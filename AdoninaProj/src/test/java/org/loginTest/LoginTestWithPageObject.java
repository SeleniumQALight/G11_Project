package org.loginTest;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;
import static org.data.TestData.INVALID_LOGIN;
import static org.data.TestData.INVALID_PASSWORD;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
  @Test
  @Category(SmokeTestsFilter.class)
  @Description("Some detailed test description")
  @Link("https://example.org")
  @Link(name = "allure", type = "mylink")
  @Issue("123")
  @Issue("432")
  @Story("Base support for bdd annotations")
  public void T0001_validLogin() {
    pageProvider.getLoginPage()
            .openPage()
            .enterTextIntoInputLogin(VALID_LOGIN)
            .enterTextIntoInputPassw0rd(VALID_PASSWORD+1)
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
