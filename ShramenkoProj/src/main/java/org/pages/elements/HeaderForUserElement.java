package org.pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.*;

public class HeaderForUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSighOut;

    @FindBy(xpath = "//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//*[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//*[@data-original-title='Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = "//img[@data-original-title='My Profile']")
    private WebElement imgAvatar;




    public HeaderForUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public HeaderForUserElement checkIsButtonSighOutVisible() {
        checkIsElementVisible(buttonSighOut);
        return this;
    }

    @Step
    public HeaderForUserElement checkIsButtonSighOutNotVisible() {
        checkIsElementNotVisible(buttonSighOut);
        return this;
    }

    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    @Step
    public HeaderForUserElement checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    @Step
    public HeaderForUserElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public boolean isButtonSighOutVisible() {
        return isElementVisible(buttonSighOut);
    }

    @Step
    public HeaderForUserElement checkIsButtonSearchVisible(){
        checkIsElementVisible(buttonSearch);
        return this;
    }

    @Step
    public HeaderForUserElement checkIsButtonSearchNotVisible(){
        checkIsElementNotVisible(buttonSearch);
        return this;
    }

    @Step
    public HeaderForUserElement checkIsButtonChatVisible(){
        checkIsElementVisible(buttonChat);
        return this;
    }

    @Step
    public HeaderForUserElement checkIsButtonChatNotVisible(){
        checkIsElementNotVisible(buttonChat);
        return this;
    }

    @Step
    public HeaderForUserElement checkIsAvatarVisible(){
        checkIsElementVisible(imgAvatar);
        return this;
    }

    @Step
    public HeaderForUserElement checkIsAvatarNotVisible(){
        checkIsElementNotVisible(imgAvatar);
        return this;
    }

    @Step
    public LoginPage clickOnButtonSignOut(){
        clickOnElement(buttonSighOut);
        return new LoginPage(webDriver);
    }

}
