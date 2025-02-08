package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void T0001_validLoginTest() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn()
        ;
//        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
//        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
//        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutVisible();

        pageProvider.getHomePage().getHeaderForUserElement()
                .checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage()
                .checkIsUsernameInputInvisible()
                .checkIsInputPasswordInvisible()
        ;

    }

    @Test
    public void T0002_invalidLoginTest() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin("invalidLogin")
                .enterTextIntoInputPassword("invalidPassword")
                .clickOnButtonSignIn()
                .getHeaderForUserElement()
                .checkIsButtonSignOutInvisible();

        pageProvider.getLoginPage()
                .checkTextInSuccessMessage("Invalid username/password.")
                .checkIsButtonSignInVisible()
        ;
    }

    @Test
    public void TC006_stayLoggedInNewTabTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();

        pageProvider.getHomePage().openNewTab();
        pageProvider.getLoginPage().switchToNewTab();
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().checkIsRedirectToHomePage();

        pageProvider.getHomePage().switchToTheFirstTab();
        pageProvider.getHomePage().checkIsRedirectToHomePage();

        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getHomePage().closeCurrentTab();

        pageProvider.getHomePage().switchToTheFirstTab();
        pageProvider.getHomePage().checkIsRedirectToHomePage();

    }

    @Test
    public void TC007_inputFieldsAreClearedAfterRefreshTest() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .refreshPage();
        pageProvider.getLoginPage()
                .clickOnButtonSignIn()
                .getHeaderForUserElement()
                .checkIsButtonSignOutInvisible();
    }

    @Test
    public void TC009_validLoginWithTabAndEnterTest() {
        pageProvider.getLoginPage()
                .openPage()
                .pressTabButton();
        pageProvider.getLoginPage().pressTabButton();
        pageProvider.getLoginPage().
                sendKeysUsingActions(VALID_LOGIN);
        pageProvider.getLoginPage().pressTabButton();
        pageProvider.getLoginPage().sendKeysUsingActions(VALID_PASSWORD);
        pageProvider.getLoginPage().pressEnterButton();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutVisible();
    }


}
