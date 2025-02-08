package org.pages;

import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForUserElement;
import org.apache.log4j.Logger;

public class HomePage extends ParentPage {

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
        getHeaderElement().checkIsButtonSighOutVisible();
        checkUrl();
        return this;
    }


    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        if (getHeaderElement().isButtonSighOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN)
                    .enterTextIntoInputPassword(TestData.VALID_PASSWORD)
                    .clickOnButtonSighIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }
}
