package org.LoginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CheckLogoutInTwoTabs extends BaseTest {

    @Test
    public void HW5_T0005_CheckLogoutInTwoTabs(){
        pageProvider.getLoginPage().openLoginAndFillLoginFormWithValidData().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().openNewTabAndOpenHomePage().switchToSelectedTabAndCheckButtonSignOut(1);
        pageProvider.getHomePage().switchToSelectedTabAndClickButtonSignOut(0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
        pageProvider.getHomePage().switchToSelectedTabAndRefreshPage(1);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
    }

}
