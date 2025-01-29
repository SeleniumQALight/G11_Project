package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForLoggedInUserElement;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());




    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedInUserElement getHeaderElement() {
        return new HeaderForLoggedInUserElement(webDriver);
    }

    public HomePage checkIsRedirectOnHomePage() {
        getHeaderElement().checkIsButtonSignOutVisible();
        //TODO check current URL
        return this;
    }



}
