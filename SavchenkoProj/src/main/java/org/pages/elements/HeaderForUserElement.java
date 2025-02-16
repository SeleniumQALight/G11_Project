package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.LoginPage;

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


    public  HeaderForUserElement(WebDriver driver) {
        super(driver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }



    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    public void checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
    }

    public void checkIsButtonMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
    }

    public void checkIsButtonSearchIsVisible() {
        checkIsElementVisible(buttonSearch);
    }

    public void checkIsButtonChatInvisible() {
        checkIsElementInvisible(buttonChat);
    }

    protected void checkIsElementInvisible(WebElement buttonChat) {
    }

    public void checkIsButtonChatIsVisible() {
        checkIsElementVisible(buttonChat);
    }

    public LoginPage clickOnSignOutButton() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public void checkIsButtonSignOutInvisible() {
        checkIsElementInvisible(buttonSignOut);
    }
    public void checkIsButtonCreatePostInvisible() {
        checkIsElementInvisible(buttonCreatePost);
    }
    public void checkIsButtonMyProfileInvisible() {
        checkIsElementInvisible(buttonMyProfile);
    }
    public void checkIsButtonSearchInvisible() {
        checkIsElementInvisible(buttonSearch);
    }
}
