package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForUserElement;


public class HomePage extends ParentPage {

    private Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webdriver) {
        super(webdriver);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webdriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        getHeaderElement().checkIsButtonSignOutVisible();
        // TODO add current url
        return this;
    }



}
