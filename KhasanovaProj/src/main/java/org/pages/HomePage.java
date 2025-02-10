package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForUserElement;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderForUserElement getHeaderForUserElement() {
        return new HeaderForUserElement(webDriver);
    }

//    private boolean isButtonSignOutVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            logger.info(state + " is element visible");
//            return state;
//        } catch (Exception e) {
//            logger.info("Element is not found");
//            return false;
//        }
//    }

    public HomePage checkIsRedirectToHomePage() {
        getHeaderForUserElement().checkIsButtonSignOutVisible();
        checkUrl();
        return this;
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        if (getHeaderForUserElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }
}
