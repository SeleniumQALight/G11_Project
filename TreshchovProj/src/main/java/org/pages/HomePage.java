package org.pages;

import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForUserElement;

public class HomePage extends ParentPage {
  //  Logger logger = Logger.getLogger(getClass());



    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        getHeaderElement().checkIsButtonSignOutVisible();
        //TODO check current URL

        return this;
    }


}
