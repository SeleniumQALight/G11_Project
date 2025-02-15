package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForUserElement;

public class HomePage extends ParrentPage {
    private Logger logger = Logger.getLogger(getClass());


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        getHeaderElement().checkIsButtonSignOutVisible();
        checkUrl();
        return this;
    }


    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        if (getHeaderElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in.");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in.");
        }
        return this;
    }
}
