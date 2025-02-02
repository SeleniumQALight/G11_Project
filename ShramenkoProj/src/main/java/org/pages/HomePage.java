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

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        getHeaderElement().checkIsButtonSighOutVisible();
        //TODO check current url
        //TODO check is button Sigh In isn't visible
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
