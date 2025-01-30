package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends ParentPage {
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;
    public HomePage(WebDriver webdriver) {
        super(webdriver);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webdriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        checkIsButtonSignOutVisible();
        // TODO add current url
        return this;
    }


    }

}
