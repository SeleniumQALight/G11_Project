package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class OpenNewTabTests extends BaseTest {

  @Test
  public void TR051_OpenNewTabWithLogInUser() {
    pageProvider.getLoginPage()
            .openPage()
            .enterTextIntoInputLogin(VALID_LOGIN)
            .enterTextIntoInputPassw0rd(VALID_PASSWORD)
            .clickOnButtonSignIn();
    pageProvider.getHomePage().checkIsRedirectOnHomePage();
    pageProvider.getHomePage().openNewTab();
    pageProvider.getLoginPage().switchToTab(1);
    pageProvider.getLoginPage().openPage();
    pageProvider.getHomePage().checkIsRedirectOnHomePage();
    pageProvider.getHomePage().switchToMainTab();
    pageProvider.getHomePage().checkIsRedirectOnHomePage();
    pageProvider.getHomePage().switchToTab(1);
    pageProvider.getHomePage().closeNewTab();
    pageProvider.getHomePage().switchToMainTab();
    pageProvider.getHomePage().checkIsRedirectOnHomePage()
    ;
  }

  @Test
  public void TR053_OpenNewTabWithLogOutUser() {
    pageProvider.getLoginPage()
            .openPage()
            .enterTextIntoInputLogin(VALID_LOGIN)
            .enterTextIntoInputPassw0rd(VALID_PASSWORD)
            .clickOnButtonSignIn();
    pageProvider.getHomePage().checkIsRedirectOnHomePage();
    pageProvider.getHomePage().openNewTab();
    pageProvider.getLoginPage().switchToTab(1);
    pageProvider.getLoginPage().openPage();
    pageProvider.getHomePage().checkIsRedirectOnHomePage();
    pageProvider.getHomePage().switchToMainTab();
    pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();
    pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
    pageProvider.getHomePage().switchToTab(1);
    pageProvider.getLoginPage().refreshPage();
    pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible()
    ;
  }
}
