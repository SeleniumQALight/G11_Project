package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    private Logger logger1 = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public void checkIsButtonSignOutVisible() {
     // Assert.assertTrue("Button Sign Out is not displayed", isElementVisible(buttonSignOut));
        checkIsElementVisible(buttonSignOut);

    }
/*    private boolean isButtonSighOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " is element visible");
            return state;
        } catch (Exception e){
            logger.info("Element is not found");
            return false;
        }
    }*/
}
