package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.pages.elements.HeaderForUserElement;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {

//    @Test
//    public void T0002_invalidLogin() {
//        pageProvider.getLoginPage().openPage();
//        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto1");
//        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
//        pageProvider.getLoginPage().clickOnButtonSignIn();
//        checkIsButtonSignOutVisible();
//        checkIsButtonSignInVisible();
//        checkIsAllertVisible();
//    }
//
//    @Test
//    public void T0001_validLogin() {
//        pageProvider.getLoginPage().openPage();
//        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
//        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
//        pageProvider.getLoginPage().clickOnButtonSignIn();
//        checkIsButtonSignOutVisible();
//    }

    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutVisible();

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
                .clickOnSignOutButton();
        pageProvider.getLoginPage()
                .checkIsRedirectToLoginPage();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonCreatePostInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonMyProfileInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSearchInvisible();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonChatInvisible();
    }


}
