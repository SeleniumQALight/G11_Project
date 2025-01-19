package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public void checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " is element visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    public void checkIsButtonSignOutNotVisible() {
        Assert.assertFalse("Button Sign Out is not visible", isButtonSignOutNotVisible());
    }

    private boolean isButtonSignOutNotVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " element is visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    public void checkIsButtonSignInVisible() {
        Assert.assertTrue("Button Sign Out is not visible", isButtonSignInVisible());
    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info(state + " is element visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    public void checkIsAllertVisible() {
        Assert.assertTrue("Allert is not visible", isAllertVisible());
    }

    private boolean isAllertVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[contains(@class, 'alert-danger') and contains(@class, 'text-center') and text()='Invalid username/password.']")).isDisplayed();
            logger.info(state + " is element visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }
}

