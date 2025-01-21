package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSighOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSighOutVisible() {
        checkIsElementVisible(buttonSighOut);
    }


}
