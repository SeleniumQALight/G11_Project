package org.LoginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestUsingActions extends BaseTest {

    @Test
    public void HW4_T003_validLoginUsingActions() {
        pageProvider.getLoginPage().openPageAndLoginUsingActions().getHeaderElement().checkIsButtonSignOutVisible();

    }
}
