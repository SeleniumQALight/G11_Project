package org.pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.MyProfilePage;

public class HeaderForUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[@href='#']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    public HeaderForUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public MyProfilePage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new MyProfilePage(webDriver);
    }


    @Step
    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }

    @Step
    public void checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
    }

    @Step
    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    @Step
    public void checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
    }

    public void checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    @Step
    public void checkIsButtonSearchVisible() {
        checkIsElementVisible(buttonSearch);}

    @Step
    public void checkIsButtonSearchNotVisible() {
        checkIsElementNotVisible(buttonSearch);}

    @Step
    public void checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);}


    public void checkIsButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat);
    }

    public void checkIsButtonMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);}

    public void checkIsButtonMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
    }

    public HeaderForUserElement checkAllElementsInHeaderOnHomePageVisible() {
        this.checkIsButtonSignOutVisible();
        this.checkIsButtonCreatePostVisible();
        this.checkIsButtonSearchVisible();
        this.checkIsButtonChatVisible();
        this.checkIsButtonMyProfileVisible();
        return new HeaderForUserElement(webDriver);
    }

    public HeaderForUserElement checkAllElementsInHeaderOnLoginPageNotVisible() {
        this.checkIsButtonSignOutNotVisible();
        this.checkIsButtonCreatePostNotVisible();
        this.checkIsButtonSearchNotVisible();
        this.checkIsButtonChatNotVisible();
        this.checkIsButtonMyProfileNotVisible();
        return new HeaderForUserElement(webDriver);
    }
}
