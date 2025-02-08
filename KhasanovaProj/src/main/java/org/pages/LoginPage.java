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


    public LoginPage openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com/";
        webDriver.get(baseUrl);
        logger.info("Login Page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
//        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + " was inputed into input UserName");
        clearAndEnterTextIntoElement(inputUserName, login);
        return this;

    }

    public LoginPage enterTextIntoInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + " was inputted into input password");
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(invalidLoginMessage, expectedMessageText);
        return this;
    }

    public LoginPage checkIsUsernameInputInvisible() {
        checkIsElementInvisible(inputUserName);
        return this;
    }

    public LoginPage checkIsInputPasswordInvisible() {
        checkIsElementInvisible(inputPassword);
        return this;
    }

    public void checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
    }

    public HomePage clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        clickOnElement(buttonSignIn);
        return new HomePage(webDriver);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String login) {
        clearAndEnterTextIntoElement(inputUserNameRegistrationForm, login);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String login) {
        clearAndEnterTextIntoElement(inputEmailRegistrationForm, login);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String login) {
        clearAndEnterTextIntoElement(inputPasswordRegistrationForm, login);
        return this;
    }

    public LoginPage checkErrorsMessage(String expectedErrors) {
        // error1;error2;error3 -> [error1, error2, error3]

        // розбиваємо стрічку на масив по ";"
        String[] messagesArray = expectedErrors.split(RegistrationValidationMessages.SEMICOLON);

        // 10 seconds for all messages to be visible
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), messagesArray.length));


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

    public LoginPage enterLogin (String validUsername) {
        actions.moveToElement(inputUserName).click().sendKeys(validUsername).perform();
        logger.info(validUsername + " was inputted into input UserName");
        return this;
    }

    public void enterPassword(String validPassword) {
        actions.moveToElement(inputPassword).click().sendKeys(validPassword).perform();
        logger.info(validPassword + " was inputted into input password");
    }
}
