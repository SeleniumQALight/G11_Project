package org.pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.*;

public class HeaderForUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@class=\"text-white mr-2 header-chat-icon\"]")
    private WebElement buttonChat;

    public HeaderForUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    @Step
    public HeaderForUserElement checkIsButtonMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
        return new HeaderForUserElement(webDriver);
    }

    @Step
    public void checkIsButtonMyProfileInvisible() {
        checkIsElementInvisible(buttonMyProfile);
    }

    @Step
    public void checkIsButtonSignOutVisible() {
//        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementVisible(buttonSignOut);
    }

    @Step
    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    @Step
    public HeaderForUserElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return new HeaderForUserElement(webDriver);
    }

    @Step
    public void checkIsButtonCreatePostInvisible() {
        checkIsElementInvisible(buttonCreatePost);
    }

    @Step
    public void checkIsButtonSignOutInvisible() {
        checkIsElementInvisible(buttonSignOut);
    }

    @Step
    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);

    }

    @Step
    public LoginPage clickOnSignOutButton() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    @Step
    public HeaderForUserElement checkIsButtonSearchIsVisible() {
        checkIsElementVisible(buttonSearch);
        return new HeaderForUserElement(webDriver);
    }

    @Step
    public void checkIsButtonSearchInvisible() {
        checkIsElementInvisible(buttonSearch);
    }

    @Step
    public HeaderForUserElement checkIsButtonChatIsVisible() {
        checkIsElementVisible(buttonChat);
        return new HeaderForUserElement(webDriver);
    }

    @Step
    public void checkIsButtonChatInvisible() {
        checkIsElementInvisible(buttonChat);
    }
}
