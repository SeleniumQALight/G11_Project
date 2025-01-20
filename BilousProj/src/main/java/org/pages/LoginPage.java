package org.pages;

import org.apache.log4j.Logger;
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



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com";
        webDriver.get(baseUrl);
        logger.info("Login Page was opened with url " + baseUrl);
    }

    public void enterTextIntoInputLogin(String login) {
        inputUserName.clear();
        inputUserName.sendKeys(login);
        logger.info(login + "was inputed into input username");
    }

    public void enterTextIntoPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info("password was inputed into Password");
    }

    public void clickInButtomSignIn() {
        buttonSignIn.click();
        logger.info("Button SighIn was clicked");
    }
}
