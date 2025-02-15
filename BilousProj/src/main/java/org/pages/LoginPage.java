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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.Utils_Custom;

import java.util.List;

public class LoginPage extends ParantPage {
    private Logger logger = Logger.getLogger(getClass());


    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement massageField;


    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;

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
        clearAndEnterTextInToElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoPassword(String password) {
        clearAndEnterTextInToElement(inputPassword, password);
        return this;
    }

    public void checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
    }

    public void isLoginFieldInVisible() {
        checkIsElementInvisible(inputUserName); }

    public void isPasswordFieldInVisible() {
        checkIsElementInvisible(inputPassword);
    }





    public void checkIsWarningMessageDisplayed() {
        checkTextInElement(massageField, "Invalid username/password.");
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoPassword(TestData.VALID_PASSWORD);
        clickInButtonSignIn();
        return new HomePage(webDriver);
    }

    public void clickInButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String login) {
        clearAndEnterTextInToElement(inputUserNameRegistrationForm, login);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextInToElement(inputEmailRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextInToElement(inputPasswordRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] messagesArray = expectedErrors.split(RegistrationValidationMessages.SEMICOLON);

        webDriverWait_10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), messagesArray.length));

        Assert.assertEquals("Number of messages", messagesArray.length, listOfActualMessages.size());

        Utils_Custom.waitABit(1);

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
}
