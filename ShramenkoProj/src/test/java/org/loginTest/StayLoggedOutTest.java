package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class StayLoggedOutTest extends BaseTest {
    @Test
    public void T00057_stayLoggedOutTest() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginWithValidCred()
                .getHeaderElement().checkIsButtonSighOutVisible();

        pageProvider.openNewTab();
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();

        pageProvider.switchToTab(); //перехід в main tab
        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutNotVisible();

        pageProvider.switchToTab();
        pageProvider.refreshPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutNotVisible();

    }
}