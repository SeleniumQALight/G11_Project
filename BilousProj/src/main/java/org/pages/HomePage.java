package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParantPage {

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSignOutVisible() {
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



}
