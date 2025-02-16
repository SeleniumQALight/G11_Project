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

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;
    // WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));  //адреса до єлемента

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSighIn;

    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement alertIncorrectLoginPassword;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationForm;

    final static String listErrorsMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listErrorsMessagesLocator)
    private List<WebElement> listOfActualMessages;

    private Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public LoginPage openPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
//        inputUserName.clear(); //почистити поле input
//        inputUserName.sendKeys(login);//посилаємо login
//        logger.info(login + " was inputted into input UserName");
        clearAndEnterTextIntoElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    public HomePage clickOnButtonSighIn() {
        clickOnElement(buttonSighIn);
        return new HomePage(webDriver);
    }

    public LoginPage checkIsButtonSighInVisible(){
        checkIsElementVisible(buttonSighIn);
        return this;
    }

    public LoginPage checkIsButtonSighInNotVisible(){
        checkIsElementNotVisible(buttonSighIn);
        return this;
    }

    public LoginPage checkIsAlertIncorrectLoginPasswordVisible(){
        checkIsElementVisible(alertIncorrectLoginPassword);
        return this;
    }

    public HomePage openLoginPageAndFillLoginWithValidCred() {
        //method повертає об'єкт класу HomePage, тому що після login ми опинимося на HomePage
        openPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN);
        this.enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSighIn();
        return new HomePage(webDriver);
    }

    public void openLoginPageAndFillLoginWithInvalidCred() {
        openPage();
        this.enterTextIntoInputLogin(TestData.INVALID_LOGIN);
        this.enterTextIntoInputPassword(TestData.INVALID_PASSWORD);
        clickOnButtonSighIn();
    }

    public LoginPage checkIsInputLoginAndPasswordVisible(){
        checkIsElementVisible(inputUserName);
        checkIsElementVisible(inputPassword);
        return this;
    }
    public void checkIsInputLoginAndPasswordNotVisible(){
        checkIsElementNotVisible(inputUserName);
        checkIsElementNotVisible(inputPassword);
    }

    public LoginPage checkIsRedirectToLoginPage() {
        checkIsButtonSighInVisible();
        //checkUrl();
        return this;
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

    public LoginPage checkErrorsMessages(String expectedErrors) {
        //error1;error2;error3 -> [error1, error2, error3]
        String[] messagesArray = expectedErrors.split(RegistrationValidationMessages.SEMICOLON);

        webDriverWait10.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath(listErrorsMessagesLocator)
                        , messagesArray.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of messages "
                , messagesArray.length, listOfActualMessages.size()); //якщо кількість повідомлень різна, то впадемо тут

        SoftAssertions softAssertions = new SoftAssertions(); //тут будемо накопичувати наші перевірки
        for (int i = 0; i < messagesArray.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(0).getText()) //чи є цей текст
                    .as("Message number " + i)
                    .isIn(messagesArray); //в цьому списку
        }

        softAssertions.assertAll();
        //це обов'язковий рядочок!!!! без нього тест завжди буде зелений
        //а це сама перевірка
        //виведе всі перевірки, які не пройшли, коли тест впаде

        return this;
    }

    public void enterLoginAndPasswordUsingTabAndEnter(){
        pressTab();
        pressTab();
        enterText("qaauto");
        pressTab();
        enterText("123456qwerty");
        pressEnter();
    }
}
