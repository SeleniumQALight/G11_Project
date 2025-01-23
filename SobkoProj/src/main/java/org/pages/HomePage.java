package org.pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webdriver) {
        super(webdriver);
    }

    public void checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not visible", isButtonSighOutVisible());
    }
    public void checkIsButtonSignOutNotVisible() {
        Assert.assertFalse("Button Sign Out is not visible", isButtonSighOutVisible());
    }

    public void checkIsButtonSignInVisible() {
        Assert.assertTrue("Button Sign Out is not visible", isButtonSighInVisible());
    }
    public void checkIsWarningMessageVisible() {
        Assert.assertTrue("Button Sign Out is not visible", isWarningMessageVisible());
    }

    private boolean isButtonSighOutVisible() {
        try {
            boolean state = webdriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    private boolean isButtonSighInVisible() {
        try {
            boolean state = webdriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }
    private boolean isWarningMessageVisible() {
        try {
            boolean state = webdriver.findElement(By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }


}
