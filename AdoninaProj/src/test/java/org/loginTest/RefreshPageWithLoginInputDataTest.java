package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class RefreshPageWithLoginInputDataTest extends BaseTest {
  @Test
  public void TR052_RefreshPageWithLoginInputData() {
    pageProvider.getLoginPage()
            .openPage()
            .enterTextIntoInputLogin(VALID_LOGIN)
            .enterTextIntoInputPassw0rd(VALID_PASSWORD)
            .refreshPage();
    pageProvider.getLoginPage().clickOnButtonSignIn()
            .checkIsErrorMessageDisplayed("Invalid username/password.");
    pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible()
    ;
  }
}
