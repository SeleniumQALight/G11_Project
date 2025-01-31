package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class LoginPage extends ParrentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@class=\'alert alert-danger text-center\']")
    private WebElement alertInvalidLoginOrPassword;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void isAlertInvalidLoginOrPasswordDisplayed() {
        checkIsElementVisible(alertInvalidLoginOrPassword);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }


    public LoginPage openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com";
        webDriver.get(baseUrl);
        logger.info("Login page was opened with the URL: " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String password) {
        clearAndEnterTextToElement(inputPassword, password);
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

}
