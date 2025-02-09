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

public class LoginPage extends ParentPage{

    private Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password'] ")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In'] ")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")//id=xpath = //input[@id='username-register']
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;

    final static String listErrorMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listErrorMessagesLocator)
    private List<WebElement> listOfActualMessages;

    @FindBy(xpath = ".//div[@class = 'alert alert-danger text-center' ]")
    private WebElement messageInvalidLoginOrPassword;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public LoginPage openPage() {//result of openPage is LoginPage

        webDriver.get(baseUrl);
        logger.info("Login Page was opened with url " + baseUrl);
return this; //returning LoginPage
    }

    public LoginPage enterTextIntoInputLogin(String login) {
       /* WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys(login);
        logger.info(login + "was input UserName");*  !!!!!!!! we have described it in @FindBy*/
        clearAndEnterTextIntoElement(inputUserName, login);
return this;

    }

    public LoginPage enterTextIntoInputPassword(String password){
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn(){
        //buttonSignIn.click(); ** before we have created method CommonActionsWithElements

        clickOnElement(buttonSignIn);
    }

    public HomePage openLoginPageAndFillFormWithValidCred() {
        openPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN);
        this.enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage enterTextIntoRegistrationUsernameField(String login) {
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

        webDriverWait10.until((ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorMessagesLocator), messagesArray.length)));

        Assert.assertEquals("Number of Messages",messagesArray.length, listOfActualMessages.size());
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < messagesArray.length; i++) {
            softAssertions.assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + i)
                    .isIn(messagesArray);

        }
        softAssertions.assertAll(); //відразу пишeмо після methood SoftAssertions softAssertions = new SoftAssertions();
        return this;
    }

    public boolean checkIfMessageInvalidLoginPasswordVisible() {
        return isElementVisible(messageInvalidLoginOrPassword);
    }
}
