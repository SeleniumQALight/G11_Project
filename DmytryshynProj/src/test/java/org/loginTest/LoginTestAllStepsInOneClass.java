package org.loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTestAllStepsInOneClass {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");

    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");

    }

    @Test
    public void validLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("qaauto was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted into input password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SignIn was clicked");

        Assert.assertTrue("Button SignOut is not visible", isButtonSignOutVisible());

    }


    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("wrongUser");
        logger.info("wrongUser was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("wrongPassword");
        logger.info("wrongPassword was inputted into input password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SignIn was clicked");

        Assert.assertFalse("Button SignOut should not be visible", isButtonSignOutVisible());
        Assert.assertTrue("Button SignIn is not visible", isButtonSignInVisible());
        Assert.assertTrue("Error message 'Invalid username/password.' is not displayed", isErrorMessageVisible());
    }

    // new method for all visible elements
    private boolean isElementVisible(String xpath) {
        try {
            boolean state = webDriver.findElement(By.xpath(xpath)).isDisplayed();
            logger.info(state + " is element visible for xpath: " + xpath);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found for xpath: " + xpath);
            return false;
        }
    }

    private boolean isButtonSignOutVisible() {
        return isElementVisible("//button[text()='Sign Out']");
    }

    private boolean isButtonSignInVisible() {
        return isElementVisible("//button[@class='btn btn-primary btn-sm']");
    }

    private boolean isErrorMessageVisible() {
        return isElementVisible("//div[text()='Invalid username/password.']");
    }

}
