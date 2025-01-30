package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForUserElement;

public class HomePage extends ParentPage {
  //  Logger logger = Logger.getLogger(getClass());



    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);

    public HomePage checkIsButtonSignOutVisible() {

        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        getHeaderElement().checkIsButtonSignOutVisible();
        //TODO check current URL

        return this;
    }

    }

    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HomePage checkIsLoginPasswordFieldVisible(){
        Assert.assertTrue("Login field is visible", isElementVisible(webDriver.findElement(By.xpath("//input[@placeholder='Username']"))));
        Assert.assertTrue("Password field is visible", isElementVisible(webDriver.findElement(By.xpath("//input[@placeholder='Password']"))));
        return this;
    }

}
