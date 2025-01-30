package org.pages.elements;

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



    public void checkIsButtonSignOutVisible() {
        // Assert.assertTrue("Button Sign Out is not displayed", isElementVisible(buttonSignOut));
        checkIsElementVisible(buttonSignOut);
    }

   public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public MyProfilePage ClickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }
}
