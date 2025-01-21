package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParrentPage {
//    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }
}
