package org.loginTest;

import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    //@Ignore - анотація для ігнорування тестів
    @Category(SmokeTestsFilter.class)
    //LoginTestWithPageObject#T0001_validLoginTest
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
    public void T0004_validSignOut() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForUserElement()
                .checkIsButtonCreatePostVisible()
                .checkIsButtonMyProfileVisible()
                .checkIsButtonSearchIsVisible()
                .checkIsButtonChatIsVisible();
        pageProvider.getHomePage().getHeaderForUserElement()
                .clickOnSignOutButton()
                .checkIsRedirectToLoginPage();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonCreatePostInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonMyProfileInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSearchInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonChatInvisible();
    }

    @Test
    public void TC006_stayLoggedInNewTabTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();

        pageProvider.getHomePage().openNewTab();
        pageProvider.getLoginPage().switchToTab(1);
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().checkIsRedirectToHomePage();

        pageProvider.getHomePage().switchToTab(0);
        pageProvider.getHomePage().checkIsRedirectToHomePage();

        pageProvider.getHomePage().switchToTab(1);
        pageProvider.getHomePage().closeCurrentTab();

        pageProvider.getHomePage().switchToTab(0);
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
