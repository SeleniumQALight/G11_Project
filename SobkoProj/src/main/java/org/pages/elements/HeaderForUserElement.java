package org.pages.elements;

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

    @FindBy(xpath = "//button[text()='Sign Out'")
    private WebElement buttonLogout;
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = "//*[@data-original-title='Chat']")
    private WebElement buttonChat;
    @FindBy(xpath = "//*[@data-original-title='Search']")
    private WebElement buttonSearch;


    public HeaderForUserElement(WebDriver webdriver) {
        super(webdriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webdriver);
    }
    public CreateNewPostPage clickOnButtonCreatePost () {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webdriver);

    }
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webdriver);
    }

    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }
    public void checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);
    }
    public void checkIsButtonSearchVisible() {
        checkIsElementVisible(buttonSearch);
    }
    public void checkIsMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
    }
    public void checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
    }

    public void checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
    }
    public void checkIsButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat);
    }
    public void checkIsButtonSearchNotVisible() {
        checkIsElementNotVisible(buttonSearch);
    }

    public void checkIsMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
    }
    public void checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
    }



    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    public HeaderForUserElement checkAllHeaderElementsVisible(){
        checkIsButtonCreatePostVisible();
        checkIsButtonSignOutVisible();
        checkIsMyProfileVisible();
        checkIsButtonChatVisible();
        checkIsButtonSearchVisible();
        return this;
    }

    public LoginPage checkHeaderElementsNotVisible(){
        checkIsButtonCreatePostNotVisible();
        checkIsButtonSignOutNotVisible();
        checkIsMyProfileNotVisible();
        checkIsButtonChatNotVisible();
        checkIsButtonSearchNotVisible();
        return new LoginPage(webdriver);
    }
}
