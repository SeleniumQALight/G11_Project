package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.data.TestData.*;

public class LoginPage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());
   @FindBy(xpath = "//input[@placeholder='Username']")
   private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//*[text()='Invalid username/password.']")
    private WebElement warningMessage;


    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement clickSignIn;

    public LoginPage(WebDriver webdriver) {
        super(webdriver);
    }

    public LoginPage openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com/";
        webdriver.get(baseUrl);
        logger.info("Login Page was opened url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
        clearAndEnterTextIntoElement(inputUserName, login);
        return this;

    }
    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(clickSignIn);
        return this;
    }

    public LoginPage checkIsInputLoginVisible() {
        checkIsElementVisible(inputUserName);
        return this;
    }
    public LoginPage checkIsInputPasswordVisible() {
        checkIsElementVisible(inputUserName);
        return this;
    }
    public LoginPage enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }


    public void clickOnButtonSignIn() {
        clickOnElement(clickSignIn);
    }

    public HomePage openLoginPageAndLoginFormWithValidCreds() {
        this.openPage();
        this.enterTextIntoInputLogin(VALID_LOGIN);
        this.enterTextIntoInputPassword(VALID_PASSWORD);
        this.clickOnButtonSignIn();
        return new HomePage(webdriver);

    }

    public void checkWarningMessageInvalidLoginPassword() {
        Assert.assertTrue("Warning message is not displayed", isElementVisible(warningMessage));
    }

    public void checkIsInputLoginNotVisible() {
        checkIsElementNotVisible(inputUserName);
    }
    public void checkIsInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }
}

