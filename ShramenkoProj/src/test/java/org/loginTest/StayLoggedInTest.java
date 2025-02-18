package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class StayLoggedInTest extends BaseTest {
    @Test
    public void T00051_stayLoggedInTest() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginWithValidCred()
                .getHeaderElement().checkIsButtonSighOutVisible();

        pageProvider.openNewTab();
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();

        pageProvider.switchToTab(); //перехід в main tab
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();

        pageProvider.closeNotActualTabAndReturnOnMainTab();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();

    }
}
