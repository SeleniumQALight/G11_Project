package org.loginTest;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWIthPageObject extends BaseTest {
    // test for valid login
    // input
    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage().openPage().enterTextIntoInputLogin(VALID_LOGIN).
                enterTextIntoInputPassword(VALID_PASSWORD).clickOnButtonSignIn();
    /*    pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.getLoginPage().clickOnButtonSignIn();

     */
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }

}
