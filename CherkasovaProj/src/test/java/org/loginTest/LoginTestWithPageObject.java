package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;


import static org.data.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void T001_validLogin(){
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
//        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
//        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
//        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkIsInputLoginNotVisible();
        pageProvider.getLoginPage().checkIsInputPasswordNotVisible();

    }

    @Test
    public void T002_invalidLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(INVALID_LOGIN)
                .enterTextIntoInputPassword(INVALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkIsButtonSignInVisible();
        pageProvider.getLoginPage().checkIsSussesMessageDisplayed();
        pageProvider.getLoginPage().checkIsRedirectToLoginPage();


    }

    @Test
    public void T003_stayLoggedAfterClosingNewTab(){
        pageProvider.getLoginPage().openPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();

        pageProvider.getHomePage().openNewTab();
        pageProvider.getLoginPage().switchToNewTab();
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().checkIsRedirectToHomePage();

        pageProvider.getHomePage().switchToMainTab();
        pageProvider.getHomePage().checkIsRedirectToHomePage();

        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getHomePage().closeCurrentTab();
        pageProvider.getHomePage().switchToMainTab();
        pageProvider.getHomePage().checkIsRedirectToHomePage();
    }

    @Test
    public void T004_dataInLoginInputsDisappearsAfterRefreshing () {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .refreshPage();
        pageProvider.getLoginPage()
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();

    }

}
