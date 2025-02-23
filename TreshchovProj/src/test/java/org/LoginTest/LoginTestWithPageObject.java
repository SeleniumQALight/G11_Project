package org.LoginTest;

import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class)
    public void t0001_validLogin() {
        pageProvider.getLoginPage().openPage().
                enterTextIntoInputLogin(VALID_LOGIN).
                enterTextIntoInputPassword(VALID_PASSWORD).
                clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();


    }


}
