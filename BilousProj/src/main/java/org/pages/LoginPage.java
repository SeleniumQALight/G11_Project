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
        clearAndEnterTextInToElement(inputUserName, login);
    }

    public void enterTextIntoPassword(String password) {
        clearAndEnterTextInToElement(inputPassword, password);
    }

    public void clickInButtomSignIn() {
        clickOnElement(buttonSignIn);
    }
}
