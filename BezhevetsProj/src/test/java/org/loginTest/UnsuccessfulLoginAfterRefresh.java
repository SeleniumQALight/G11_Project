package org.loginTest;

import org.baseTest.BaseTest;
import org.categories.SmokeTestFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class UnsuccessfulLoginAfterRefresh extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
    public void T00016_unsuccessfulLoginAfterRefresh() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD).refreshPage();

        // Натиснути кнопку SignIn
        pageProvider.getLoginPage().clickOnButtonSignIn();

        // Перевірити, що кнопка SignOut не показується
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
    }

}
