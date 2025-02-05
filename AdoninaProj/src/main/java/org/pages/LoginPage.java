package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
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

  @FindBy(xpath = "//*[@class='alert alert-danger text-center']")
  private WebElement errorMessage;


  public LoginPage(WebDriver webDriver) {
    super(webDriver);
  }

  @Override
  protected String getRelativeUrl() {
    return "/";
  }

  public LoginPage openPage() {

    webDriver.get(baseUrl);
    logger.info("Login Page was opened with url " + baseUrl);
    return this;
  }

  public LoginPage enterTextIntoInputLogin(String login) {
//    WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//    inputUserName.clear();
//    inputUserName.sendKeys(login);
//    logger.info(login + " was inputted into input UserName");
    clearAndEnterTextIntoInput(inputUserName, login);
    return this;
  }

  public LoginPage enterTextIntoInputPassw0rd(String password) {
//    WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//    inputPassword.clear();
//    inputPassword.sendKeys(password);
//    logger.info(password + " was inputted into input password");
    clearAndEnterTextIntoInput(inputPassword, password);
    return this;
  }

  public LoginPage clickOnButtonSignIn() {
    clickOnElement(buttonSignIn);
    logger.info("Button Sign In was clicked");
    return this;
  }

  public HomePage openLoginPageAndFillLoginFormWithValidCred() {
    openPage();
    this.enterTextIntoInputLogin(TestData.VALID_LOGIN);
    this.enterTextIntoInputPassw0rd(TestData.VALID_PASSWORD);
    clickOnButtonSignIn();
    return new HomePage(webDriver);
  }

  public void checkIsButtonSignInVisible() {
    checkIsElementVisible(buttonSignIn);
  }

  public LoginPage checkIsErrorMessageDisplayed(String expectedText) {
    checkTextInElement(errorMessage, expectedText);
    return this;
  }


  public LoginPage checkIsInputUsernameNotVisible() {
    checkIsElementNotVisible(inputUserName);
    return this;
  }

  public LoginPage checkIsInputPasswordNotVisible() {
    checkIsElementNotVisible(inputPassword);
    return this;
  }
}
