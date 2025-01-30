package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForUserElement;

public class HomePage extends ParentPage{
    private Logger logger = Logger.getLogger(getClass());


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }




    public void checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
    }

    public HomePage checkIsRedirectToHomePage() {
        getHeaderElement().checkIsButtonSignOutVisible();
        //TODO check current URL
        return this;
    }

    public void checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

}
