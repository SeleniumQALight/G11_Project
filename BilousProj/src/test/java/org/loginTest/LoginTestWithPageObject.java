package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
    @Test
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
        Actions actions = new Actions(webDriver);
        // Step 1: Open login page
        pageProvider.getLoginPage().openPage();
        // Step 2: Press Tab key
        actions.sendKeys(Keys.TAB).perform();
        // Step 3: Press Tab key
        actions.sendKeys(Keys.TAB).perform();
        // Step 4: Enter login into input Login
        actions.sendKeys(VALID_LOGIN).perform();
        // Step 5: Press Tab key
        actions.sendKeys(Keys.TAB).perform();
        // Step 6: Enter password into input Password
        actions.sendKeys(VALID_PASSWORD).perform();
        // Step 7: Press Enter key
        actions.sendKeys(Keys.ENTER).perform();
        // Step 8: Check that button SignOut is visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }

    @Test
    public void T0007_checkInputsDisappearAfterRefresh() {
        // Step 1: Open login page
        pageProvider.getLoginPage().openPage();
        // Step 2: Enter 'qaauto' login into input Login
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        // Step 3: Enter '123456qwerty' password into input Password
        pageProvider.getLoginPage().enterTextIntoPassword("123456qwerty");
        // Step 4: Refresh page
        webDriver.navigate().refresh();
        // Step 5: Click on button SignIn
        pageProvider.getLoginPage().clickInButtonSignIn();
        // Step 6: Check that button SignOut is not visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutInvisible();
    }

    @Test
    public void T0008_checkSignOutButtonVisibilityAcrossTabs() {
        // Step 1: Open login page
        pageProvider.getLoginPage().openPage();

        // Step 2: Login with valid credentials
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoPassword(VALID_PASSWORD)
                .clickInButtonSignIn();

        // Step 3: Check that button SignOut is visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        // Step 4: Open new tab in browser using JavaScript
        ((JavascriptExecutor) webDriver).executeScript("window.open()");

        // Step 5: Switch to new tab
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));

        // Step 6: Open login page
        pageProvider.getLoginPage().openPage();

        // Step 7: Check that button SignOut is visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        // Step 8: Switch to main tab
        webDriver.switchTo().window(tabs.get(0));

        // Step 9: Check that button SignOut is visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        // Step 10: Close new tab and switch to main tab
        webDriver.switchTo().window(tabs.get(1)).close();
        webDriver.switchTo().window(tabs.get(0));

        // Step 11: Check that button SignOut is visible
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
        ((JavascriptExecutor) webDriver).executeScript("window.open()");

        // Step 5: Switch to new tab
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));

        // Step 6: Open login page
        pageProvider.getLoginPage().openPage();

        // Step 7: Check that button SignOut is visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        // Step 8: Switch to main tab
        webDriver.switchTo().window(tabs.get(0));

        // Step 9: Click on button SignOut
        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();

        // Step 10: Check that button SignOut is not visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutInvisible();

        // Step 11: Switch to new tab
        webDriver.switchTo().window(tabs.get(1));

        // Step 12: Refresh page
        webDriver.navigate().refresh();

        // Step 13: Check that button SignOut is not visible
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutInvisible();
    }

}
