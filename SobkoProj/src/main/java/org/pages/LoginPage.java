package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
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

    public LoginPage enterTextIntoInputPassword(String password) {
        //WebElement inputPassword = webdriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + "was inputted into input password");
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
}

