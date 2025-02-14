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
        pageProvider.getLoginPage().switchToTab(1);
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().checkIsRedirectToHomePage();

        pageProvider.getHomePage().switchToTab(0);
        pageProvider.getHomePage().getHeaderForUserElement().clickOnButtonSignOut();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutInvisible();

        pageProvider.getHomePage().switchToTab(1);
        pageProvider.getHomePage().refreshPage();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutInvisible();
    }
}
