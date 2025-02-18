package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestUsingTabAndEnter extends BaseTest {
    @Test
    public void T00055_validLoginUsingTabAndEnterButtons() {
        pageProvider.getLoginPage()
                .openPage()
                .enterLoginAndPasswordUsingTabAndEnter();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();

    }
}
