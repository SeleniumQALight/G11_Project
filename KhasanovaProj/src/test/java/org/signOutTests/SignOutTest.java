package org.signOutTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void TC008_userIsLoggedOutInAllTabsTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();

        pageProvider.getHomePage().openNewTab();
        pageProvider.getLoginPage().switchToNewTab();
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().checkIsRedirectToHomePage();

        pageProvider.getHomePage().switchToTheFirstTab();
        pageProvider.getHomePage().getHeaderForUserElement().clickOnButtonSignOut();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutInvisible();

        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getHomePage().refreshPage();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutInvisible();
    }
}
