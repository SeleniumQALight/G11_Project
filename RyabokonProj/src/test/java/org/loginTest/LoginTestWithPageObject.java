package org.loginTest;


import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;


public class LoginTestWithPageObject extends BaseTest {

    // Test case for
    @Test
    public void T001_validLogin() {
// chain method
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }

@Test
    public void T002a_invalidLogin() {
    pageProvider.getLoginPage().openPage();
    pageProvider.getLoginPage().enterTextIntoInputLogin("qaato1");
    pageProvider.getLoginPage().enterTextIntoInputPassword("123456");
    pageProvider.getLoginPage().clickOnButtonSignIn();
    pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
}
}

