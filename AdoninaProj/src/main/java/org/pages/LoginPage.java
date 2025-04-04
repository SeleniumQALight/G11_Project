package org.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.RegistrationValidationMessages;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.Utils_Custom;

import java.util.List;

public class LoginPage extends ParentPage {
  private Logger logger = Logger.getLogger(getClass());

  @FindBy(xpath = "//input[@placeholder='Username']")
  private WebElement inputUserName;

  @FindBy(xpath = "//input[@placeholder='Password']")
  private WebElement inputPassword;

  @FindBy(xpath = "//button[text()='Sign In']")
  private WebElement buttonSignIn;

  @FindBy(xpath = "//button[text()='Sign up for OurApp']")
  private WebElement buttonSignUp;

  @FindBy(xpath = "//*[@class='alert alert-danger text-center']")
  private WebElement errorMessage;

  @FindBy(id = "username-register")
  private WebElement inputUserNameRegistrationForm;

  @FindBy(id = "email-register")
  private WebElement inputEmailRegistrationForm;

  @FindBy(id = "password-register")
  private WebElement inputPasswordRegistrationForm;

  final static String listErrorMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
  @FindBy(xpath = listErrorMessagesLocator)
  private List<WebElement> listOfActualMessages;

  @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
  private WebElement warningMessageInCenter;

  public LoginPage(WebDriver webDriver) {
    super(webDriver);
  }

  @Override
  protected String getRelativeUrl() {
    return "/";
  }

  @Step
  public LoginPage openPage() {

    webDriver.get(baseUrl);
    logger.info("Login Page was opened with url " + baseUrl);
    return this;
  }

  @Step
  public LoginPage enterTextIntoInputLogin(String login) {
//    WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//    inputUserName.clear();
//    inputUserName.sendKeys(login);
//    logger.info(login + " was inputted into input UserName");
    clearAndEnterTextIntoInput(inputUserName, login);
    return this;
  }

  @Step
  public LoginPage enterTextIntoInputPassw0rd(String password) {
//    WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//    inputPassword.clear();
//    inputPassword.sendKeys(password);
//    logger.info(password + " was inputted into input password");
    clearAndEnterTextIntoInput(inputPassword, password);
    return this;
  }

  @Step
  public LoginPage clickOnButtonSignIn() {
    clickOnElement(buttonSignIn);
    logger.info("Button Sign In was clicked");
    return this;
  }

  @Step
  public HomePage openLoginPageAndFillLoginFormWithValidCred() {
    openPage();
    this.enterTextIntoInputLogin(TestData.VALID_LOGIN);
    this.enterTextIntoInputPassw0rd(TestData.VALID_PASSWORD);
    clickOnButtonSignIn();
    return new HomePage(webDriver);
  }

  @Step
  public LoginPage checkIsErrorMessageDisplayed(String expectedText) {
    checkTextInElement(errorMessage, expectedText);
    return this;
  }

  @Step
  public LoginPage checkIsInputUsernameNotVisible() {
    checkIsElementNotVisible(inputUserName);
    return this;
  }

  @Step
  public LoginPage checkIsInputPasswordNotVisible() {
    checkIsElementNotVisible(inputPassword);
    return this;
  }

  @Step
  public LoginPage enterTextIntoRegistrationUserNameField(String login) {
    clearAndEnterTextIntoInput(inputUserNameRegistrationForm, login);
    return this;
  }

  @Step
  public LoginPage enterTextIntoRegistrationEmailField(String login) {
    clearAndEnterTextIntoInput(inputEmailRegistrationForm, login);
    return this;
  }

  @Step
  public LoginPage enterTextIntoRegistrationPasswordField(String login) {
    clearAndEnterTextIntoInput(inputPasswordRegistrationForm, login);
    return this;
  }

  @Step
  public LoginPage checkIsInputLoginVisible() {
    checkIsElementVisible(inputUserName);
    return this;
  }

  @Step
  public LoginPage checkIsInputPasswordVisible() {
    checkIsElementVisible(inputPassword);
    return this;
  }

  @Step
  public LoginPage checkIsButtonSignInVisible() {
    checkIsElementVisible(buttonSignIn);
    return this;
  }

  @Step
  public LoginPage checkAllElementsOnLoginPageInHeaderVisible() {
    this.checkIsInputLoginVisible();
    this.checkIsInputPasswordVisible();
    this.checkIsButtonSignInVisible();
    return new LoginPage(webDriver);
  }

  @Step
  public LoginPage checkErrorsMessages(String expectedErrors) {
    // error1;error2;error3 -> [error1, error2, error3]
    String[] messagesArray = expectedErrors.split(RegistrationValidationMessages.SEMICOLON);

    webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorMessagesLocator)
            , messagesArray.length));

    Utils_Custom.waitABit(1);

    Assert.assertEquals("Number of messages ", messagesArray.length, listOfActualMessages.size());

    SoftAssertions softAssertions = new SoftAssertions();
    for (int i = 0; i < messagesArray.length; i++) {
      softAssertions
              .assertThat(listOfActualMessages.get(i).getText())
              .as("Message number " + i)
              .isIn(messagesArray);
    }
    softAssertions.assertAll();
    return this;
  }

  public LoginPage checkTextInAlertInCenter(String errorMessage) {
    checkTextInElement(warningMessageInCenter, errorMessage);
    return this;
  }

  public LoginPage clickOnButtonSignUp() {
    clickOnElement(buttonSignUp);
    logger.info("Button Sign Up was clicked");
    return this;
  }
}
