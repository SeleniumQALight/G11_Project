package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class UnsuccessfulLoginAfterRefresh extends BaseTest {
    @Test
    public void T00016_unsuccessfulLoginAfterRefresh() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin("qaauto")
                .enterTextIntoInputPassword("123456qwerty");

        getDriver().navigate().refresh();

        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();

    }

}
