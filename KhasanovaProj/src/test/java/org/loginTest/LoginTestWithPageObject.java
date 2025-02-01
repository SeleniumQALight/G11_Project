package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void T0001_validLogin() {
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
    public void T0002_invalidLogin() {
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
                .checkIsButtonCreatePostVisible();
        pageProvider.getHomePage().getHeaderForUserElement()
                .checkIsButtonMyProfileVisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSearchIsVisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonChatIsVisible();
        pageProvider.getHomePage().getHeaderForUserElement()
                .clickOnSignOutButton()
                .checkIsInputPasswordIsVisible()
                .checkIsUsernameInputIsVisible()
                .checkIsButtonSignInVisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonCreatePostInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonMyProfileInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSearchInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonChatInvisible();
    }

}
