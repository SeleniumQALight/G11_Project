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

public class InvalidLoginTestAllStepsInOneClass {

    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    //preconditions
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
    }

    //post-conditions
    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }

    //Test
    @Test
    public void invalidLogin() {

        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        //знайти інпут, почистити його, ввести нашу інфу:
        WebElement inputLogin = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputLogin.clear(); //чистимо
        inputLogin.sendKeys("allash1900"); //невірний логін
        logger.info("Incorrect login was inputted into input UserName");

        //password
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("Correct password was inputted into input Password");

        //button Sigh In
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SighIn was clicked");

        //check
        //в скобках перше - це повідомлення коли тест не пройдено, а друге як будемо перевіряти:
        Assert.assertFalse("Button SighOut is visible = Login is correct", isButtonSighOutVisible());
        Assert.assertTrue("Button SighIn isn't visible = Login is correct", isButtonSighInVisible());
        Assert.assertTrue("Warning is visible", isAlertIncorrectLoginPassword());
    }

    private boolean isButtonSighOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Button SignOut is visible");
            return state;
        } catch (Exception e) {
            logger.info("Button SighOut isn't visible");
            return false;
        }
    }

    private boolean isButtonSighInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info("Button SighIn is visible");
            return state;
        } catch (Exception e) {
            logger.info("Button SighIn isn't visible");
            return false;
        }
    }

    private boolean isAlertIncorrectLoginPassword() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info("Warning is visible");
            return state;
        } catch (Exception e) {
            logger.info("Warning isn't visible");
            return false;
        }
    }


}
