package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParantPage {
    private Logger logger = Logger.getLogger(getClass());


    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement massageField;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com";
        webDriver.get(baseUrl);
        logger.info("Login Page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
        clearAndEnterTextInToElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoPassword(String password) {
        clearAndEnterTextInToElement(inputPassword, password);
        return this;
    }

    public void checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
    }

    public void isLoginFieldInVisible() {
        checkIsElementInvisible(inputUserName); }

    public void isPasswordFieldInVisible() {
        checkIsElementInvisible(inputPassword);
    }





    public void checkIsWarningMessageDisplayed() {
        checkTextInElement(massageField, "Invalid username/password.");
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoPassword(TestData.VALID_PASSWORD);
        clickInButtomSignIn();
        return new HomePage(webDriver);
    }

    public void clickInButtomSignIn() {
        clickOnElement(buttonSignIn);
    }

}
