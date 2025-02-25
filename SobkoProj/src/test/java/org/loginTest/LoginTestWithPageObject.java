package org.loginTest;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    public final static String INVALID_LOGIN = "qaau1to";
    public final static String INVALID_PASSWORD = "123456qwert1y";

    //test case for valid login
    @Test
    @Category(SmokeTestsFilter.class)
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    public void T0001_validLogin() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD+1)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkIsInputLoginNotVisible();
        pageProvider.getLoginPage().checkIsInputPasswordNotVisible();
    }

    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(INVALID_LOGIN)
                .enterTextIntoInputPassword(INVALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getLoginPage().checkIsButtonSignInVisible();
        pageProvider.getLoginPage().checkWarningMessageInvalidLoginPassword();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();

    }

    @Test
    public void TC003_LoggedInUserStaysLoggedInWhenSwitchingTabs() {
        pageProvider.getLoginPage().openLoginPageAndLoginFormWithValidCreds()
                .getHeaderElement().checkIsButtonSignOutVisible();

        browserActions.openNewTab();
        browserActions.switchToTabByIndex(1);
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        browserActions.switchToTabByIndex(0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        browserActions.switchToTabByIndex(1);
        browserActions.closeCurrentTab();
        browserActions.switchToTabByIndex(0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

    }

    @Test
    public void T0004_inputCredentialsAndReload() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD);
        browserActions.reloadPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();
    }


    @Test
    public void T0005_pressKeysToLogIn() {
        pageProvider.getLoginPage().useTabAndEnterToSetCredentialsAndLogin(VALID_LOGIN, VALID_PASSWORD)
                .getHeaderElement().checkIsButtonSignOutVisible();
    }
    @Test
    public void TC006_LoggedOutUserIsLoggedOutWhenSwitchingTabs() {
        pageProvider.getLoginPage().openLoginPageAndLoginFormWithValidCreds()
                .getHeaderElement().checkIsButtonSignOutVisible();

        browserActions.openNewTab();
        browserActions.switchToTabByIndex(1);
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

       browserActions.switchToTabByIndex(0);
        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();

        browserActions.switchToTabByIndex(1);
        browserActions.reloadPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotVisible();


    }

    @Test
    public void T0003_signOut() {
        pageProvider.getLoginPage()
                .openLoginPageAndLoginFormWithValidCreds()
                .checkIsRedirectToHomePage()
                .getHeaderElement().checkAllHeaderElementsVisible()
                .clickOnButtonSignOut()
                .checkHeaderElementsNotVisible();
        pageProvider.getLoginPage().checkIsButtonSignInVisible()
                .checkIsInputLoginVisible()
                .checkIsInputPasswordVisible();


    }
}
