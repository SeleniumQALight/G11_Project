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
    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement invalidLoginOrPasswordMessage;
    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistrationForm;
    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationForm;
    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;

    final static String listErrorrsMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listErrorrsMessagesLocator)
    private List<WebElement> listOfErrorsMessages;
    @FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfActualMessages;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    public LoginPage checkIsUsernameInputIsVisible() {
        checkIsElementVisible(inputUserName);
        return this;
    }

    public LoginPage checkIsInputPasswordIsVisible() {
        checkIsElementVisible(inputPassword);
        return this;
    }

    protected String getRelativeUrl() {
        return "/";
    }

    public LoginPage openPage() {
        webDriver.get(baseUrl);
        logger.info("Login Page was opened with url" + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
        clearAndEnterTextIntoElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String password) {
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

    public LoginPage checkIsRedirectToLoginPage() {
        checkIsElementVisible(buttonSignIn);
        checkIsUsernameInputIsVisible();
        checkIsInputPasswordIsVisible();
        //TODO check current URL
        return this;
    }



    public LoginPage checkIsInvalidLoginOrPasswordMessageVisible() {
        checkIsElementVisible(invalidLoginOrPasswordMessage);
        return this;
    }

    public void checkIsInputLoginOrPasswordNotVisible() {
        checkIsElementNotVisible(inputUserName);
        checkIsElementNotVisible(inputPassword);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String login) {
        clearAndEnterTextIntoElement(inputUserNameRegistrationForm, login);
        return this;

    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorMessages(String expectedErrors) {
        //error1;error2;error3; -> [error1, error2, error3]
        String[] messagesArray = expectedErrors.split(RegistrationValidationMessages.SEMICOLON);

        webDriverWait_10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorrsMessagesLocator), messagesArray.length));
        Assert.assertEquals("Number of messages ", messagesArray.length, listOfActualMessages.size());

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
