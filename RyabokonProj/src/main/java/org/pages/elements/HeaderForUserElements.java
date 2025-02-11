package org.pages.elements;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.MyProfilePage;

public class HeaderForUserElements  extends CommonActionsWithElements {

    public HeaderForUserElements(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = ".//img[@alt ='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;


    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

@FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

@FindBy(xpath = ".//img[@alt='My profile']")
    private WebElement buttonAvatar;

@FindBy(xpath = ".//*[@data-original-title='Chat']") //.//*[@data-original-title="Chat"]
    private WebElement buttonChat;

@FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement InputLogin;

@FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement InputPassword;

    public void checkIsButtonSignOutVisible() {
        // Assert.assertTrue("Button Sign Out is not displayed", isElementVisible(buttonSignOut));
        checkIsElementVisible(buttonSignOut);
    }

   public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }


    public boolean checkIsButtonSignInVisible() {return isElementVisible(buttonSignIn); }

    public void checkIsButtonSignOutInvisible() {
        Assert.assertFalse("Button Sign Out is not displayed  ", isElementVisible(buttonSignOut));
    }

    public void checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
    }

    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }

    public void checkIsButtonAvatarVisible() {
        checkIsElementVisible(buttonAvatar);
    }

    public void checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);
    }

    public void checkIsInputLoginVisible() {
        checkIsElementVisible(InputLogin);
    }

    public void checkIsInputPasswordVisible() {
        checkIsElementVisible(InputPassword);
    }

    public void checkIsButtonCreatePostInVisible() {
        Assert.assertFalse("Button CreatePost is not displayed", isElementVisible(buttonCreatePost));

    }

    public void checkIsButtonAvatarInVisible() {
        Assert.assertFalse("Button Avatar is not displayed", isElementVisible(buttonAvatar));
    }

    public void checkIsButtonChatInVisible() {
        Assert.assertFalse("Button Chat is not displayed", isElementVisible(buttonChat));
    }
}


