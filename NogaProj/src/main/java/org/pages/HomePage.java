package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());




    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public HomePage checkIsRedirectOnHomePage() {
        getHeaderElement().checkIsButtonSignOutVisible();
        //TODO check current URL
        return this;
    }


}
