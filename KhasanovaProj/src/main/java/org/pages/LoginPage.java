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

    @FindBy(xpath = "//div[@class=\"alert alert-danger text-center\"]")
    private WebElement invalidLoginMessage;

    @FindBy(id = "username-register") // = xpath = "//input[@id='username-register']"
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

    @Step
    public LoginPage openPage() {
        webDriver.get(baseUrl);
        logger.info("Login Page was opened with url " + baseUrl);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputLogin(String login) {
//        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + " was inputed into input UserName");
        clearAndEnterTextIntoElement(inputUserName, login);
        return this;

    }

    @Step
    public LoginPage enterTextIntoInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + " was inputted into input password");
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(invalidLoginMessage, expectedMessageText);
        return this;
    }

    @Step
    public LoginPage checkIsUsernameInputInvisible() {
        checkIsElementInvisible(inputUserName);
        return this;
    }

    @Step
    public LoginPage checkIsUsernameInputIsVisible() {
        checkIsElementVisible(inputUserName);
        return this;
    }

    @Step
    public LoginPage checkIsInputPasswordInvisible() {
        checkIsElementInvisible(inputPassword);
        return this;
    }

    @Step
    public LoginPage checkIsInputPasswordIsVisible() {
        checkIsElementVisible(inputPassword);
        return this;
    }

    @Step
    public void checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
    }

    @Step
    public HomePage clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        clickOnElement(buttonSignIn);
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage checkIsRedirectToLoginPage() {
        checkIsElementVisible(buttonSignIn);
        checkIsUsernameInputIsVisible();
        checkIsInputPasswordIsVisible();
        checkUrl();
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String login) {
        clearAndEnterTextIntoElement(inputUserNameRegistrationForm, login);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailRegistrationForm, email);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordRegistrationForm, password);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessage(String expectedErrors) {
        // error1;error2;error3 -> [error1, error2, error3]

        // розбиваємо стрічку на масив по ";"
        String[] messagesArray = expectedErrors.split(RegistrationValidationMessages.SEMICOLON);

        // 10 seconds for all messages to be visible
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), messagesArray.length));

        Utils_Custom.waitABit(1); // wait for all messages to be visible

        // перевіряємо, що кількість помилок на сторінці співпадає з кількістю очікуваних помилок
        Assert.assertEquals("Number of messages", messagesArray.length, listOfActualMessages.size());


        SoftAssertions softAssertions = new SoftAssertions();   // for multiple soft asserts

        // перевіряємо текст кожної помилки
        for (int i = 0; i < messagesArray.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + i)
                    .isIn(messagesArray); // тут ми перевіряємо, що текст помилки є в масиві з очікуваними помилками
        }

        softAssertions.assertAll(); // тут будуть виводитися меседжі про всі помилки, якщо є
        // тобто тест падає тут після всіх перевірок
        //без цього кроку тест завжди буде проходити

        return this;
    }


}
