package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage().openPage().enterTextIntoInputLogin(VALID_LOGIN).enterTextIntoPassword(VALID_PASSWORD).clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutVisible();

    }
}
