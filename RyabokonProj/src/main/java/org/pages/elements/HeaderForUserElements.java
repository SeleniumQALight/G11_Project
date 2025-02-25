package org.pages.elements;

import io.qameta.allure.Step;
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

    @FindBy(xpath = "//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;


    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

@FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

@FindBy(xpath = ".//img[@alt='My profile']")
    private WebElement buttonAvatar;

@FindBy(xpath = ".//*[@data-original-title='Chat']") //.//*[@data-original-title="Chat"]
    private WebElement buttonChat;

    @Step
    public void checkIsButtonSignOutVisible() {
        // Assert.assertTrue("Button Sign Out is not displayed", isElementVisible(buttonSignOut));
        checkIsElementVisible(buttonSignOut);
    }

   public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }
    @Step
    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    @Step
    public boolean checkIsButtonSignInVisible() {return isElementVisible(buttonSignIn); }

    @Step
    public void checkIsButtonSignOutNotVisible() {
        Assert.assertFalse("Button Sign Out is not displayed  ", isElementVisible(buttonSignOut));
    }
    @Step
    public void checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
    }
    @Step
    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }
    @Step
    public void checkIsButtonAvatarVisible() {
        checkIsElementVisible(buttonAvatar);
    }
    @Step
    public void checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);
    }




    @Step
    public void checkIsButtonCreatePostInVisible() {
        Assert.assertFalse("Button CreatePost is not displayed", isElementVisible(buttonCreatePost));

    }
    @Step
    public void checkIsButtonAvatarInVisible() {
        Assert.assertFalse("Button Avatar is not displayed", isElementVisible(buttonAvatar));
    }

    public void checkIsButtonChatInVisible() {
        Assert.assertFalse("Button Chat is not displayed", isElementVisible(buttonChat));
    }
}


