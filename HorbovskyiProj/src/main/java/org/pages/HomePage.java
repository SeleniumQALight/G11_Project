package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedInUserElement;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public HeaderForLoggedInUserElement getHeaderElement() {
        return new HeaderForLoggedInUserElement(webDriver);
    }

    public HomePage checkIsRedirectOnHomePage() {
        getHeaderElement().checkIsButtonSignOutVisible();
        //TODO check current URL
        return this;
    }


    public HomePage openHomePageIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        if (getHeaderElement().isButtonSignOutVisible()) {
            logger.info("User is already loged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectOnHomePage();
            logger.info("User was not loged in. Login performed");
        }
        return this;
    }

    public LoginPage checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }


}
