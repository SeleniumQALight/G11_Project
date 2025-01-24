package org.loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTestAllStepsInOneClass extends BaseTest {
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
    public void tearDowwn() {
        webDriver.quit();
        logger.info("Browser was closed");
    }


    @Test
    public void validLogin() {
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
    public void T0002_invalidLogin() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto1");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();
        checkIsButtonSignOutVisible();
        checkIsButtonSignInVisible();
        checkIsAllertVisible();
    }

    @Test
    public void T0001_validLogin() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();
        checkIsButtonSignOutVisible();
    }

    public void checkIsButtonSignOutVisible() {
        Assert.assertFalse("Button Sign Out is not visible", isButtonSignOutVisible());
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
