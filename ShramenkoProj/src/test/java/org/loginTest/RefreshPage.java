package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class RefreshPage extends BaseTest {
    @Test
    public void T00052_refreshPageTest() {

        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin("qaauto")
                .enterTextIntoInputPassword("123456qwerty");
        pageProvider.refreshPage();
        pageProvider.getLoginPage().clickOnButtonSighIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutNotVisible();

    }

}
