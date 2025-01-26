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

    @FindBy(xpath = "//div[@class=\"alert alert-danger text-center\"]")
    private WebElement invalidLoginMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getInputUserName() {
        return inputUserName;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public LoginPage openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com/";
        webDriver.get(baseUrl);
        logger.info("Login Page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
//        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + " was inputed into input UserName");
        clearAndEnterTextIntoElement(inputUserName, login);
        return this;

    }

    public LoginPage enterTextIntoInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + " was inputted into input password");
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
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

    public LoginPage checkIsButtonSignOutInvisible() {
        HomePage homePage = new HomePage(webDriver);
        homePage.checkIsElementInvisible(homePage.getButtonSignOut());
        return this;
    }

    public LoginPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(invalidLoginMessage, expectedMessageText);
        return this;
    }


}
