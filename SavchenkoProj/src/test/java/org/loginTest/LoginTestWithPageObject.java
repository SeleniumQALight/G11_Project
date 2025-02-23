package org.loginTest;

import org.baseTest.BaseTest;
import org.categories.SmokeTestFilter;
import org.data.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.pages.elements.HeaderForUserElement;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(TestData.INVALID_LOGIN)
                .enterTextIntoInputPassword(TestData.INVALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getLoginPage().checkIsUsernameInputIsVisible()
                .checkIsInvalidLoginOrPasswordMessageVisible()
        ;
    }
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
    @Category(SmokeTestFilter.class)
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

    @Test
    public void T0004_checkSignOutButtonAfterOpeningNewTab() throws InterruptedException {

        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderForUserElement().isButtonSignOutVisible();
        pageProvider.getPostPage().openNewTab();
        pageProvider.getPostPage().switchToNewTab();
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().getHeaderForUserElement().isButtonSignOutVisible();
        pageProvider.getPostPage().switchToFirstTab();
        pageProvider.getHomePage().getHeaderForUserElement().isButtonSignOutVisible();
        pageProvider.getPostPage().closeCurrentTab();
        pageProvider.getHomePage().getHeaderForUserElement().isButtonSignOutVisible();
    }

    @Test
    public void T0005_checkLoginDataDisappearAfterRefresh() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        boolean isSignOutVisible = pageProvider.getHomePage().getHeaderForUserElement().isButtonSignOutVisible();
        Assert.assertFalse("Sign Out button should not be visible after refresh", isSignOutVisible);
    }




}
