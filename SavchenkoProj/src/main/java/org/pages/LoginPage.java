package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
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
    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
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
}
