package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSighOut;

    @FindBy(xpath = "//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSighOutVisible() {
        checkIsElementVisible(buttonSighOut);
    }

    public void checkIsButtonSighOutNotVisible() {
        checkIsElementNotVisible(buttonSighOut);
    }

    public HomePage checkIsRedirectToHomePage() {
        checkIsButtonSighOutVisible();
        //TODO check current url
        //TODO check is button Sigh In isn't visible
        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }
}
