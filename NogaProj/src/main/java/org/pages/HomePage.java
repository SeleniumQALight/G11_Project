package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    }

    private boolean isButtonSignOutVisible() {
        try {
            //          boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            boolean state = buttonSignOut.isDisplayed();
            logger.info(state + " is visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }
}
