package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.MyProfilePage;

public class HeaderForUserElement extends CommonActionsWithElements {


    @FindBy(xpath = ".//img[@alt='/My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out'")
    private WebElement buttonLogout;
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;


    public HeaderForUserElement(WebDriver webdriver) {
        super(webdriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webdriver);
    }
    public CreateNewPostPage clickOnButtonCreatePost () {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webdriver);

    }
    public void checkIsButtonSignOutVisible() {

        checkIsElementVisible(buttonSignOut);
    }

}
