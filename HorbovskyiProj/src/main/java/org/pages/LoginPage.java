package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.By;
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

    public LoginPage openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com/";
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
}
