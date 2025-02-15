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

    final static String listErrorMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listErrorMessagesLocator)
    private List<WebElement> lsitOfActualMessages;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public LoginPage openPage() {
        webDriver.get(baseUrl);
        logger.info("Login Page was opened with URL " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
        //  WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login +
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
        return this;
    }

    public HomePage checkIsInputLoginOrPasswordNotVisible() {
        checkIsElementNotVisible(inputUserName);
        checkIsElementNotVisible(inputPassword);
        return new HomePage(webDriver);
    }

    public LoginPage checkIsInvalidLoginOrPasswordMessageVisible() {
        checkIsElementVisible(invalidLoginOrPasswordMessage);
        return this;
    }

    public LoginPage enterTextIntoRegistaionUserNameField(String login) {
        clearAndEnterTextToElement(inputUserNameRegistrationForm, login);
        return this;
    }
    public LoginPage enterTextIntoRegistaionEmailField(String email) {
        clearAndEnterTextToElement(inputEmailRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistaionPasswordField(String password) {
        clearAndEnterTextToElement(inputPasswordRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] errorsArray = expectedErrors.split(RegistrationValidationMessages.SEMICOLON);

        webDriverWait_10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorMessagesLocator), errorsArray.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of Messages", errorsArray.length, lsitOfActualMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(lsitOfActualMessages.get(i).getText())
                    .as("Message number " + i)
                    .isIn(errorsArray);
        }

        softAssertions.assertAll();
    return this;
    }
}
