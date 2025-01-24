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
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto111");
        logger.info("qaauto was inputed into input username");

        WebElement inputPassport = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassport.clear();
        inputPassport.sendKeys("123456qwerty");
        logger.info("password was inputed into Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SighIn was clicked");

        Assert.assertTrue("Button SignOut is not visible", isButtonSighOutVisible());


    }

    private boolean isButtonSighOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " is element visible ");
            return state;
        } catch (Exception e) {
            logger.info("Elenent is not found");
            return false;
        }

    }


}
