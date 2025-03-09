package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class RefreshPageWithLoginInputDataTest extends BaseTest {
    @Test
    public void TR002_RefreshPageWithLoginInputData() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().checkIsInvalidLoginOrPasswordMessageVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible()
        ;
    }
}
