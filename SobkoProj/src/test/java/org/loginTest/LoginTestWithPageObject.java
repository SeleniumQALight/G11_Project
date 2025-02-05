package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;


public class LoginTestWithPageObject extends BaseTest {
    public final static String INVALID_LOGIN = "qaau1to";
    public final static String INVALID_PASSWORD="123456qwert1y";
    //test case for valid login
    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkIsInputLoginNotVisible();
        pageProvider.getLoginPage().checkIsInputPasswordNotVisible();
    }

    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(INVALID_LOGIN)
                .enterTextIntoInputPassword(INVALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getLoginPage().checkIsButtonSignInVisible();
        pageProvider.getLoginPage().checkWarningMessageInvalidLoginPassword();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();

    }

}
