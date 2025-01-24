package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webdriver) {
        super(webdriver);
    }

    public void checkIsButtonSignOutVisible() {

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
}
