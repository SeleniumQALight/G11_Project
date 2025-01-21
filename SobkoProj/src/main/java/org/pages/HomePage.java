package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends ParentPage {
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    private Logger logger = Logger.getLogger(getClass());
    public HomePage(WebDriver webdriver) {
        super(webdriver);
    }
    public void checkIsButtonSignOutVisible() {
       checkIsElementVisible(buttonSignOut);
    }

//
//    private boolean isButtonSighOutVisible() {
//        try {
//            boolean state = webdriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            return state;
//        } catch (Exception e) {
//            logger.info("Element is not found");
//            return false;
//        }
//    }

}
