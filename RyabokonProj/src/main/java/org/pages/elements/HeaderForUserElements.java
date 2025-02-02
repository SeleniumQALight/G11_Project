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
}

