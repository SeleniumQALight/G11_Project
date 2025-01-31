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

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }

    public HeaderForUserElement checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
        return this;
    }


    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public HeaderForUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
        return new LoginPage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }
}
