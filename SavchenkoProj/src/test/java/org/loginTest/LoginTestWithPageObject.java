package org.loginTest;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {

    private WebDriver webDriver;

    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(TestData.INVALID_LOGIN)
                .enterTextIntoInputPassword(TestData.INVALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getLoginPage().checkIsButtonSignInVisible()
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
    public void T0001_validLogin() {
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkIsInputLoginOrPasswordNotVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

    }

    @Test
    public void T0004_checkSignOutButtonAfterOpeningNewTab() throws InterruptedException {

        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible();
        pageProvider.getPostPage().openNewTab();
        pageProvider.getPostPage().switchToNewTab();
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible();
        pageProvider.getPostPage().switchToFirstTab();
        pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible();
        pageProvider.getPostPage().closeCurrentTab();
        pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible();
    }

    @Test
    public void T0005_checkLoginDataDisappearAfterRefresh() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        boolean isSignOutVisible = pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible();
        Assert.assertFalse("Sign Out button should not be visible after refresh", isSignOutVisible);
    }


}
