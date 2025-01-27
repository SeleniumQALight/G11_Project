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

public class InvalidLoginInOneClass {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    //precondition
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
    public void TR002_invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");
        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto1");
        logger.info("Invalid login 'qaauto1' was input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was input into input password");


        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SignIn was clicked");

        Assert.assertFalse("Button Sign Out should not be visible", isButtonSignOutVisible());
        Assert.assertTrue("Button Sign In is visible", isButtonSignInVisible());
        Assert.assertTrue("Invalid message password", invalidPasswordOrLogin());

    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + "Sign Out is visible");
            return state;
        } catch (Exception e) {
            logger.info("Sign out button is not found");
            return false;
        }
    }

    private boolean isButtonSignInVisible() {
        try{
        boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
        logger.info(state + " Sign In is visible");
        return state;
    } catch(Exception e) {
        logger.info("Sign in button is not found");
        return false;
    }
}

private boolean invalidPasswordOrLogin() {
   try {
       boolean state = webDriver.findElement(By.xpath(".//div[@class = 'alert alert-danger text-center' ]")).isDisplayed();
       logger.info(state + " Invalid message password/login is shown");
       return state;
   } catch(Exception e) {
       logger.info("Invalid message password/login is shown");
       return false;
   }
}
}