package org.LoginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class CheckLoginInAnotherTabTest extends BaseTest {

    @Test
    public void HW5_T0001_validLogin() {
        pageProvider.getLoginPage().openPage().
                enterTextIntoInputLogin(VALID_LOGIN).
                enterTextIntoInputPassword(VALID_PASSWORD).
                clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        pageProvider.getLoginPage().openNewTabAndOpenHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().switchToSelectedTabAndCheckButtonSignOut(0).closeSelectedTab(1);
        pageProvider.getHomePage().switchToSelectedTabAndCheckButtonSignOut(0);

    }


}
