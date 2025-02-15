package org.LoginTest;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;

public class CheckLoginDataDisappearAfterRefresh extends BaseTest {

    @Test
    public void HW4_T0002_CheckLoginDataDisappearAfterRefresh() {
        pageProvider.getLoginPage().openPage().
                enterTextIntoInputLogin(VALID_LOGIN).
                enterTextIntoInputPassword(TestData.VALID_PASSWORD).refreshLoginPage().clickOnButtonSignIn();

        pageProvider.getLoginPage().getHeaderElement().checkIsButtonSignOutNotVisible();

    }
}
