package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;



    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;
//    private Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public void checkIsButtonSignOutVisible() {
        //     Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementVisible(buttonSignOut);
    }

    public HomePage checkIsRedirectToHomePage() {
        checkIsButtonSignOutVisible();
        //TODO check current URL
        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HomePage checkIsInputLoginOrPasswordNotVisible() {
        checkIsElementNotVisible(inputUserName);
        checkIsElementNotVisible(inputPassword);
        return this;
    }
}

