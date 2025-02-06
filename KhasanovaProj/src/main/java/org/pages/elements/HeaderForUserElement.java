package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public HeaderForUserElement checkIsButtonMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
        return new HeaderForUserElement(webDriver);
    }

    public void checkIsButtonMyProfileInvisible() {
        checkIsElementInvisible(buttonMyProfile);
    }

    public void checkIsButtonSignOutVisible() {
//        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementVisible(buttonSignOut);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public HeaderForUserElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return new HeaderForUserElement(webDriver);
    }

    public void checkIsButtonCreatePostInvisible() {
        checkIsElementInvisible(buttonCreatePost);
    }

    public void checkIsButtonSignOutInvisible() {
        checkIsElementInvisible(buttonSignOut);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);

    }

    public LoginPage clickOnSignOutButton() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public HeaderForUserElement checkIsButtonSearchIsVisible() {
        checkIsElementVisible(buttonSearch);
        return new HeaderForUserElement(webDriver);
    }

    public void checkIsButtonSearchInvisible() {
        checkIsElementInvisible(buttonSearch);
    }

    public HeaderForUserElement checkIsButtonChatIsVisible() {
        checkIsElementVisible(buttonChat);
        return new HeaderForUserElement(webDriver);
    }

    public void checkIsButtonChatInvisible() {
        checkIsElementInvisible(buttonChat);
    }
}
