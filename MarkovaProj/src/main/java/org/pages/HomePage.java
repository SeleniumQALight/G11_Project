package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSignOutVisible() {
        // Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementVisible(buttonSignOut);

    }

    public HomePage checkIsRedirectOnHomePage() {
        checkIsButtonSignOutVisible();
        //TODO check current url
        return this;
    }
    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        if (isButtonSignOutVisible()) {
            logger.info("User is already logged in.");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN);
                    loginPage.enterTextIntoPassword(TestData.VALID_PASSWORD);
                    loginPage.clickOnButtonSignIn();
                    checkIsRedirectOnHomePage();
                    logger.info("User was not logged in");
        }
        return this;
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }
        public MyProfilePage clickOnButtonMyProfile() {
            clickOnElement(buttonMyProfile);
            return new MyProfilePage(webDriver);
        }

}
