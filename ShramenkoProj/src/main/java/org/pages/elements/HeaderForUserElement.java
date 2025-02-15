package org.pages.elements;

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

    public HeaderForUserElement checkIsButtonSighOutVisible() {
        checkIsElementVisible(buttonSighOut);
        return this;
    }

    public HeaderForUserElement checkIsButtonSighOutNotVisible() {
        checkIsElementNotVisible(buttonSighOut);
        return this;
    }


    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public HeaderForUserElement checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    public HeaderForUserElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public boolean isButtonSighOutVisible() {
        return isElementVisible(buttonSighOut);
    }

    public HeaderForUserElement checkIsButtonSearchVisible(){
        checkIsElementVisible(buttonSearch);
        return this;
    }
    public HeaderForUserElement checkIsButtonSearchNotVisible(){
        checkIsElementNotVisible(buttonSearch);
        return this;
    }

    public HeaderForUserElement checkIsButtonChatVisible(){
        checkIsElementVisible(buttonChat);
        return this;
    }
    public HeaderForUserElement checkIsButtonChatNotVisible(){
        checkIsElementNotVisible(buttonChat);
        return this;
    }

    public HeaderForUserElement checkIsAvatarVisible(){
        checkIsElementVisible(imgAvatar);
        return this;
    }
    public HeaderForUserElement checkIsAvatarNotVisible(){
        checkIsElementNotVisible(imgAvatar);
        return this;
    }

    public LoginPage clickOnButtonSignOut(){
        clickOnElement(buttonSighOut);
        return new LoginPage(webDriver);
    }

}
