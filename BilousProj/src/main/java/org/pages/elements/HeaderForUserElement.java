package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.MyProfilePage;

public class HeaderForUserElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttomMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }

    public HeaderForUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtomMyProfile() {
        clickOnElement(buttomMyProfile);
        return new MyProfilePage(webDriver);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }
    public void checkIsButtonCreatePostIsVisible() {
        checkIsElementVisible(buttonCreatePost);
    }

    public void checkIsButtonSignOutInvisible() { checkIsElementInvisible(buttonSignOut); }

}
