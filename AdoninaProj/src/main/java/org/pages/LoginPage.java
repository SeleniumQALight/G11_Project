package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
  private Logger logger = Logger.getLogger(getClass());

  @FindBy(xpath = "//input[@placeholder='Username']")
  private WebElement inputUserName;

  @FindBy(xpath = "//input[@placeholder='Password']")
  private WebElement inputPassword;

  @FindBy(xpath = "//button[text()='Sign In']")
  private WebElement buttonSignIn;

  public LoginPage(WebDriver webDriver) {
    super(webDriver);
  }

  public void openPage() {
    String baseUrl = "https://aqa-complexapp.onrender.com";
    webDriver.get(baseUrl);
    logger.info("Login Page was opened with url " + baseUrl);
  }

  public void enterTextIntoInputLogin(String login) {
//    WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//    inputUserName.clear();
//    inputUserName.sendKeys(login);
//    logger.info(login + " was inputted into input UserName");
    clearAndEnterTextIntoInput(inputUserName, login);
  }

  public void enterTextIntoInputPassw0rd(String password) {
//    WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//    inputPassword.clear();
//    inputPassword.sendKeys(password);
//    logger.info(password + " was inputted into input password");
    clearAndEnterTextIntoInput(inputPassword, password);
  }

  public void clickOnButtonSignIn() {
    clickOnElement(buttonSignIn);
  }
}
