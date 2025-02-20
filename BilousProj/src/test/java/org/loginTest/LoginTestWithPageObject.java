package org.loginTest;

import org.baseTest.BaseTest;
import org.categories.SmokeTestFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
    public void T0001_validLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoPassword(VALID_PASSWORD)
                .clickInButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

    }

    @Test
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin("invalidLogin")
                .enterTextIntoPassword("0000000000")
                .clickInButtonSignIn();

        pageProvider.getLoginPage().checkIsButtonSignInVisible();
        pageProvider.getLoginPage().checkIsWarningMessageDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutInvisible();

        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostIsVisible();
        pageProvider.getLoginPage().isLoginFieldInVisible();
        pageProvider.getLoginPage().isPasswordFieldInVisible();

    }

    @Test
    public void T0004_SignOut() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred();
        pageProvider.getHomePage().getHeaderElement().checkAllElementsInHeaderIsVisible();
        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();
        pageProvider.getHomePage().getHeaderElement().checkAllElementsInHeaderIsInvisible();
    }


    @Test
    public void T0006_validLogin() {

        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().pressTabKey(2);
        pageProvider.getLoginPage().enterTextIntoField(VALID_LOGIN);
        pageProvider.getLoginPage().pressTabKey(1);
        pageProvider.getLoginPage().enterTextIntoField(VALID_PASSWORD);
        pageProvider.getLoginPage().pressEnter();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }

    @Test
    public void T0007_checkInputsDisappearAfterRefresh() {

        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoPassword("123456qwerty");
        pageProvider.getLoginPage().refreshPage();
        pageProvider.getLoginPage().clickInButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutInvisible();
    }

    @Test
    public void T0008_checkSignOutButtonVisibilityAcrossTabs() {

        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoPassword(VALID_PASSWORD)
                .clickInButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().openNewTab();
        pageProvider.getLoginPage().switchToTab(1);
        pageProvider.getLoginPage().openPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().switchToTab(0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().switchToTab(1);
        pageProvider.getLoginPage().closeCurrentTab();
        pageProvider.getLoginPage().switchToTab(0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }

    @Test
    public void T0010_checkSignOutButtonVisibilityAndLogoutAcrossTabs() {
        // Step 1: Open login page
        pageProvider.getLoginPage().openPage();
        // Step 2: Login with valid credentials
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoPassword(VALID_PASSWORD)
                .clickInButtonSignIn();
        // Step 3: Check that button SignOut is visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        // Step 4: Open new tab in browser using JavaScript
        pageProvider.getLoginPage().openNewTab();

        // Step 5: Switch to new tab
        pageProvider.getLoginPage().switchToTab(1);

        // Step 6: Open login page
        pageProvider.getLoginPage().openPage();

        // Step 7: Check that button SignOut is visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        // Step 8: Switch to main tab
        pageProvider.getLoginPage().switchToTab(0);

        // Step 9: Click on button SignOut
        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();

        // Step 10: Check that button SignOut is not visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutInvisible();

        // Step 11: Switch to new tab
        pageProvider.getLoginPage().switchToTab(1);

        // Step 12: Refresh page
        pageProvider.getLoginPage().refreshPage();

        // Step 13: Check that button SignOut is not visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutInvisible();
    }

}
