package org.pages;

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
import org.pages.elements.HeaderForUserElement;
import org.utils.Utils_Custom;

import java.util.List;

public class LoginPage extends ParentPage { //create constructor
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement invalidUsernamePasswordMessage;

    final static String listErrorsMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listErrorsMessagesLocator)
    private List<WebElement> listOfActualMessages;

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
//        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + " was inputted into input UserName");
        clearAndEnterTextIntoElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + " password was inputted into input password");
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String login) {
        clearAndEnterTextIntoElement(inputUserNameRegistrationForm, login);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailInRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordInRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        // error1;error2;error3 -> [error1, error2, error3]
        String[] messagesArray = expectedErrors.split(RegistrationValidationMessages.SEMICOLON);

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator)
                , messagesArray.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of messages", messagesArray.length, listOfActualMessages.size());

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

    public LoginPage checkIsInvalidUsernamePasswordMessageVisible() {
        checkIsElementVisible(invalidUsernamePasswordMessage);
        return this;
    }

    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
        return this;
    }


    public LoginPage checkIsInputUserNameRegistrationFormNotVisible() {
        checkIsElementNotVisible(inputUserNameRegistrationForm);
        return this;
    }

    public LoginPage checkIsInputEmailInRegistrationFormNotVisible() {
        checkIsElementNotVisible(inputEmailInRegistrationForm);
        return this;
    }
}
