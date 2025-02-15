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

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

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

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public LoginPage openPage() {

        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
        enterTextIntoInput(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public HomePage openLoginAndFillLoginFormWithValidData() {
        openPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();

        return new HomePage(webDriver);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        enterTextIntoInput(inputUserNameRegistrationField, userName);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String Email) {
        enterTextIntoInput(inputEmailRegistrationField, Email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String Password) {
        enterTextIntoInput(inputPasswordRegistrationField, Password);
        return this;
    }

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

}
