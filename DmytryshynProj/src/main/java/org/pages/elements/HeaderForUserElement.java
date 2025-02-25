package org.pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.LoginPage;
import org.pages.MyProfilePage;

public class HeaderForUserElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = ".//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    public HeaderForUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public LoginPage clickOnButtonSingOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    @Step
    public HeaderForUserElement checkIsButtonSingOutVisible() {
//        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public HeaderForUserElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HeaderForUserElement checkIsButtonSingOutNotVisible() {
        //        Assert.assertFalse("Button Sign Out is visible", isButtonSignOutVisible());
        checkIsElementNotVisible(buttonSignOut);
        return this;
    }

    @Step
    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    @Step
    public boolean isButtonSingOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    public HeaderForUserElement checkIsButtonMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
        return this;
    }

    public HeaderForUserElement checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);
        return this;
    }

    public HeaderForUserElement checkIsSearchVisible() {
        checkIsElementVisible(buttonSearch);
        return this;
    }

    public HeaderForUserElement checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    public HeaderForUserElement checkIsButtonMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
        return this;
    }

    public HeaderForUserElement checkIsButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat);
        return this;
    }

    public HeaderForUserElement checkIsSearchNotVisible() {
        checkIsElementNotVisible(buttonSearch);
        return this;
    }
}



