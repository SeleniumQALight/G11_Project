package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.LoginPage;
import org.pages.MyProfilePage;

public class HeaderForLoggedInUserElement extends CommonActionsWithElements {

    @FindBy(xpath = ".//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public static final String buttonSearchLocator = "//a[@class='text-white mr-2 header-search-icon']";
    @FindBy(xpath = "//a[@class='text-white mr-2 header-search-icon']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement buttonChat;

    public HeaderForLoggedInUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }

    public LoginPage checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public void checkIsSearchButtonVisible() {
        checkIsElementVisible(buttonSearch);
    }

    public void checkIsChatButtonVisible() {
        checkIsElementVisible(buttonChat);
    }

    public void checkIsMyProfileButtonVisible() {
        checkIsElementVisible(buttonMyProfile);
    }

    public void checkIsCreatePostButtonVisible() {
        checkIsElementVisible(buttonCreatePost);
    }

    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }
}
