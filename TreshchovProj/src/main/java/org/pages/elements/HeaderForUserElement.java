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
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    public HeaderForUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }

    @Step
    public CreateNewPostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    @Step
    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    @Step
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public void checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
    }

    public void checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);
    }
    public void checkIsButtonSearchVisible() {
        checkIsElementVisible(buttonSearch);
    }

    public void checkIsButtonMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
    }

    public void checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
    }

    public void checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
    }

    public void checkIsButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat);
    }

    public void checkIsButtonSearchNotVisible() {
        checkIsElementNotVisible(buttonSearch);
    }

    public void checkIsButtonMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
    }

}
