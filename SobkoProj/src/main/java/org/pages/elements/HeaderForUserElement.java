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
    public HeaderForUserElement clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return this;
    }
    @Step
    public void checkIsButtonSignOutVisible() {

        checkIsElementVisible(buttonSignOut);
    }
    @Step
    public void checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);
    }
    @Step
    public void checkIsButtonSearchVisible() {
        checkIsElementVisible(buttonSearch);
    }
    @Step
    public void checkIsMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
    }
    @Step
    public void checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
    }
    @Step
    public void checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
    }
    @Step
    public void checkIsButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat);
    }
    @Step
    public void checkIsButtonSearchNotVisible() {
        checkIsElementNotVisible(buttonSearch);
    }
    @Step
    public void checkIsMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
    }
    @Step
    public void checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
    }



    @Step
    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }
    @Step
    public HeaderForUserElement checkAllHeaderElementsVisible(){
        checkIsButtonCreatePostVisible();
        checkIsButtonSignOutVisible();
        checkIsMyProfileVisible();
        checkIsButtonChatVisible();
        checkIsButtonSearchVisible();
        return this;
    }
    @Step
    public LoginPage checkHeaderElementsNotVisible(){
        checkIsButtonCreatePostNotVisible();
        checkIsButtonSignOutNotVisible();
        checkIsMyProfileNotVisible();
        checkIsButtonChatNotVisible();
        checkIsButtonSearchNotVisible();
        return new LoginPage(webdriver);
    }
}
