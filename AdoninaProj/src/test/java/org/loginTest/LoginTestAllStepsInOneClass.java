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

    WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
    inputUserName.clear();
    inputUserName.sendKeys("qaauto");
    logger.info("qaauto was inputted into input UserName");

    WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
    inputPassword.clear();
    inputPassword.sendKeys("123456qwerty");
    logger.info("Password was inputted into input password");

    webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
    logger.info("Button SingIn was clicked");

    Assert.assertTrue("Button SignOut is not visible", isButtonSignOutVisible());


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
  @Test
  public void invalidLogin() {
    webDriver.get("https://aqa-complexapp.onrender.com");
    logger.info("Site was opened");

    WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
    inputUserName.clear();
    inputUserName.sendKeys("invalidUser");
    logger.info("invalidUser was inputted into input UserName");

    WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
    inputPassword.clear();
    inputPassword.sendKeys("invalidPassword");
    logger.info("invalidPassword was inputted into input password");

    webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
    logger.info("Button SignIn was clicked");

    WebElement errorMessage = webDriver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
    Assert.assertTrue("Error message is not displayed", errorMessage.isDisplayed());
    Assert.assertEquals("Error message text is not as expected", "Invalid username/password.", errorMessage.getText());

    Assert.assertFalse("Button Sign Out is visible", isButtonSignOutVisible());

    WebElement signInButton = webDriver.findElement(By.xpath("//button[text()='Sign In']"));
    Assert.assertTrue("Button Sign In is not visible", signInButton.isDisplayed());
  }
}
