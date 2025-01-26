package org.LoginTest;

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
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");


    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");

    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("qaauto was inputted into input username");
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted into input password");
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("button sign in was clicked");

        Assert.assertTrue("Button Sign out is not visible", isButtonSignOutVisible());

        webDriver.findElement(By.xpath("//button[text()='Sign Out']")).click();
        logger.info("button sign out was clicked");
        WebElement inputUserNameInvalid = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameInvalid.clear();
        inputUserNameInvalid.sendKeys("qaauto");
        logger.info("qaauto was inputted into input username");
        WebElement inputPasswordInvalid = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPasswordInvalid.clear();
        inputPasswordInvalid.sendKeys("123456qwe");
        logger.info("incorrect password was inputted into input password");
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("button sign in was clicked");

        Assert.assertFalse("Sign out button is not visible", isButtonSignOutVisible());
        Assert.assertTrue("Sign in button is visible", isButtonSignInVisible());

        Assert.assertTrue("Invalid username/password message is visible", isLoginErrorVisible());


    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info("Sign in is visible " + state);
            return state;
        } catch (Exception e) {
            logger.info("Sign in button is not found");
            return false;
        }
    }

    private boolean isLoginErrorVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info("Error message is visible " + state);
            return state;
        } catch (Exception e) {
            logger.info("Error message is not found");
            return false;
        }
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element is visible " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }


}
