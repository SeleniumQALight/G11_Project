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
    private WebElement buttomMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//span[@data-placement='bottom']")
    private WebElement buttonChat;

    @FindBy(xpath = "//a[@href='#']")
    private WebElement buttonSearch;


    public HeaderForUserElement(WebDriver webDriver) {
        super(webDriver);
    }


    public void checkIsButtonSignOutVisible() { checkIsElementVisible(buttonSignOut); }
    public void checkIsButtonSignOutInvisible() { checkIsElementInvisible(buttonSignOut); }

    public void checkIsButtonCreatePostIsVisible() { checkIsElementVisible(buttonCreatePost); }
    public void checkIsButtonCreatePostIsInvisible() { checkIsElementInvisible(buttonCreatePost); }

    public void checkIsButtonMyProfileVisible() { checkIsElementVisible(buttomMyProfile); }
    public void checkIsButtonMyProfileInvisible() { checkIsElementInvisible(buttomMyProfile); }

    public void checkIsButtonChatVisible() { checkIsElementVisible(buttonChat); }
    public void checkIsButtonChatInvisible() { checkIsElementInvisible(buttonChat); }

    public void checkIsButtonSearchVisible() { checkIsElementVisible(buttonSearch); }
    public void checkIsButtonSearchInvisible() { checkIsElementInvisible(buttonSearch); }

    public void checkAllElementsInHeaderIsVisible() {
        checkIsButtonSignOutVisible();
        checkIsButtonCreatePostIsVisible();
        checkIsButtonMyProfileVisible();
        checkIsButtonChatVisible();
        checkIsButtonSearchVisible();
    }
    public void checkAllElementsInHeaderIsInvisible() {
        checkIsButtonSignOutInvisible();
        checkIsButtonCreatePostIsInvisible();
        checkIsButtonMyProfileInvisible();
        checkIsButtonChatInvisible();
        checkIsButtonSearchInvisible();
    }




    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttomMyProfile);
        return new MyProfilePage(webDriver);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }


    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }



}
