package org.pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.RegistrationValidationMessages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.data.TestData.*;

public class LoginPage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//*[text()='Invalid username/password.']")
    private WebElement warningMessage;


    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement clickSignIn;
    @FindBy(xpath = "//input[@placeholder='Pick a username']")
    private WebElement inputUserNameRegistrationForm;
    @FindBy(xpath = "//input[@placeholder='you@example.com']")
    private WebElement inputUserEmailRegistrationForm;
    @FindBy(xpath = "//input[@placeholder='Create a password']")
    private WebElement inputUserPasswordRegistrationForm;
   final static String listErrorsMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listErrorsMessagesLocator)
    private List<WebElement> listOfActualMessages;


    public LoginPage(WebDriver webdriver) {
        super(webdriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public LoginPage openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com/";
        webdriver.get(baseUrl);
        logger.info("Login Page was opened url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
        clearAndEnterTextIntoElement(inputUserName, login);
        return this;

    }

    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(clickSignIn);
        return this;
    }

    public LoginPage checkIsInputLoginVisible() {
        checkIsElementVisible(inputUserName);
        return this;
    }
    public LoginPage checkIsInputPasswordVisible() {
        checkIsElementVisible(inputUserName);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }


    public void clickOnButtonSignIn() {
        clickOnElement(clickSignIn);
    }

    public HomePage openLoginPageAndLoginFormWithValidCreds() {
        this.openPage();
        this.enterTextIntoInputLogin(VALID_LOGIN);
        this.enterTextIntoInputPassword(VALID_PASSWORD);
        this.clickOnButtonSignIn();
        return new HomePage(webdriver);

    }

    public void checkWarningMessageInvalidLoginPassword() {
        Assert.assertTrue("Warning message is not displayed", isElementVisible(warningMessage));
    }

    public void checkIsInputLoginNotVisible() {
        checkIsElementNotVisible(inputUserName);
    }

    public void checkIsInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }

    public LoginPage enterTextIntoRegistrationNameField(String login) {
        clearAndEnterTextIntoElement(inputUserNameRegistrationForm, login);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputUserEmailRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputUserPasswordRegistrationForm, password);
        return this;
    }


    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] messagesArray = expectedErrors.split(RegistrationValidationMessages.SEMICOLON);
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator)
                , messagesArray.length));
        Assert.assertEquals("Number of messages", messagesArray.length, listOfActualMessages.size());

        SoftAssertions  softAssertions = new SoftAssertions();
        for (int i = 0; i < messagesArray.length; i++) {
            softAssertions.assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + i)
                    .isIn(messagesArray);

        }
        softAssertions.assertAll();
        return this;
    }
}

