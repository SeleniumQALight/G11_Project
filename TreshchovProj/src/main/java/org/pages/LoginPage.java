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
import org.pages.elements.HeaderForUserElement;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement errorMessage;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistrationField;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationField;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationField;

    final static String listErrorsMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listErrorsMessagesLocator)
    private List<WebElement> listOfActualMessages;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }


    public LoginPage openPage() {

        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputLogin(String login) {
        enterTextIntoInput(inputUserName, login);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
        return this;
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public LoginPage clickOnButtonSignInWithInvalidData() {
        clickOnElement(buttonSignIn);
        return this;
    }

    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
        return this;
    }


    public LoginPage checkIsErrorMessageVisible() {
        checkIsElementVisible(errorMessage);
        return this;
    }

    @Step
    public HomePage openLoginAndFillLoginFormWithValidData() {
        openPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();

        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        enterTextIntoInput(inputUserNameRegistrationField, userName);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String Email) {
        enterTextIntoInput(inputEmailRegistrationField, Email);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String Password) {
        enterTextIntoInput(inputPasswordRegistrationField, Password);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] messagesArray = expectedErrors.split(RegistrationValidationMessages.SEMICOLON);

        webDriverWait_10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), messagesArray.length));

        Assert.assertEquals("Number of messages ", messagesArray.length, listOfActualMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < messagesArray.length; i++) {
            softAssertions.assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + i)
                    .isIn(messagesArray);

        }

        Utils_Custom.waitABit(1);

        softAssertions.assertAll();

        return this;
    }

    public LoginPage checkIsLoginPasswordFieldNotVisible(){
        checkIsElementNotVisible(inputUserName);
        checkIsElementNotVisible(inputPassword);
        return this;
    }



    public HomePage openNewTabAndOpenHomePage() {
        openNewTab();
        switchToTab(1);
        openPage();
        return new HomePage(webDriver);
    }

    public LoginPage refreshLoginPage(){
        refreshPage();
        return new LoginPage(webDriver);
    }

    public HomePage openPageAndLoginUsingActions(){
        openPage();
        useTabKey();
        useTabKey();
        enterTextUsingActions("qaauto");
        useTabKey();
        enterTextUsingActions("123456qwerty");
        useEnterKey();
        return new HomePage(webDriver);
    }

    public LoginPage openPageAndMoveToRegistrationFieldUserNameUsingActions(){
        openPage();
        useTabKey();
        useTabKey();
        useTabKey();
        useTabKey();
        useTabKey();
        return this;
    }

    public LoginPage enterValueUsingActions(String value){
        enterTextUsingActions(value);
        return this;
    }

    public LoginPage switchToNextFieldUsingActions(){
        useTabKey();
        return this;
    }

    public LoginPage checkErrorMessageText(String expectedMessage){
        checkTextInElement(errorMessage, expectedMessage);
        return this;
    }

}
