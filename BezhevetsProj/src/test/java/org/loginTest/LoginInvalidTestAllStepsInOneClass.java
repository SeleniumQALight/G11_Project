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

public class LoginInvalidTestAllStepsInOneClass {
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
        logger.info("The 'qaauto' username was entered into the 'Username' field");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("invalidPassword"); //invalid password
        logger.info("Password was entered into the 'Password' field");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("The 'Sign In' button was clicked");

        Assert.assertFalse("Button 'Sign Out' is displayed on the page'", isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is not visible'", isButtonSignInVisible());
        Assert.assertTrue("Banner 'Invalid username/password' is not visible'", isInvalidCredsBannerVisible());


    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info("The 'Sign In' button is visible");
            return state;
        } catch (Exception e) {
            logger.info("The 'Sign In' button is not found");
            return false;
        }
    }

    private boolean isInvalidCredsBannerVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[contains(text(),'Invalid')]")).isDisplayed();
            logger.info("The 'Invalid username/password' banner is displayed");
            return state;
        } catch (Exception e) {
            logger.info("The banner is not found");
            return false;
        }
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("The 'Sign Out' button is visible");
            return state;
        } catch (Exception e) {
            logger.info("The 'Sign Out' button is not found");
            return false;
        }
    }

}

