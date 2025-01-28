package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        checkIsButtonSignOutVisible();
        //TODO check current URL

        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HomePage checkIsLoginPasswordFieldVisible(){
        Assert.assertTrue("Login field is visible", isElementVisible(webDriver.findElement(By.xpath("//input[@placeholder='Username']"))));
        Assert.assertTrue("Password field is visible", isElementVisible(webDriver.findElement(By.xpath("//input[@placeholder='Password']"))));
        return this;
    }
}
