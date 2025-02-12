package org.pages.elements;

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

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//*[@data-icon=\"comment\"]")
    private WebElement chatButton;

    @FindBy(xpath = "//*[@data-original-title=\"Search\"]//*[@data-icon=\"search\"]")
    private WebElement searchButton;

    public HeaderForUserElement checkIsMyProfileButtonVisible() {
        checkIsElementVisible(buttonMyProfile);
        return this;
    }

    public HeaderForUserElement checkIsMyProfileButtonNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
        return this;
    }

    public HeaderForUserElement checkIsChatButtonVisible() {
        checkIsElementVisible(chatButton);
        return this;
    }

    public HeaderForUserElement checkIsChatButtonNotVisible() {
        checkIsElementNotVisible(chatButton);
        return this;
    }

    public HeaderForUserElement checkIsSearchButtonVisible() {
        checkIsElementVisible(searchButton);
        return this;
    }

    public HeaderForUserElement checkIsSearchButtonNotVisible() {
        checkIsElementNotVisible(searchButton);
        return this;
    }

    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }

    public HeaderForUserElement checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
        return this;
    }

    public HeaderForUserElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HeaderForUserElement checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public HeaderForUserElement(WebDriver webDriver) {
        super(webDriver);
    }


    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }
}
