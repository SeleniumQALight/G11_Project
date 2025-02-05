package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;
    // WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));  //адреса до єлемента

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSighIn;

    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement alertIncorrectLoginPassword;

    private Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public LoginPage openPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String login) {
//        inputUserName.clear(); //почистити поле input
//        inputUserName.sendKeys(login);//посилаємо login
//        logger.info(login + " was inputted into input UserName");
        clearAndEnterTextIntoElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSighIn() {
        clickOnElement(buttonSighIn);
    }

    public void checkIsButtonSighInVisible(){
        checkIsElementVisible(buttonSighIn);
    }

    public void checkIsButtonSighInNotVisible(){
        checkIsElementNotVisible(buttonSighIn);
    }

    public void checkIsAlertIncorrectLoginPasswordVisible(){
        checkIsElementVisible(alertIncorrectLoginPassword);
    }

    public HomePage openLoginPageAndFillLoginWithValidCred() {
        //method повертає об'єкт класу HomePage, тому що після login ми опинимося на HomePage
        openPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN);
        this.enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSighIn();
        return new HomePage(webDriver);
    }

    public void openLoginPageAndFillLoginWithInvalidCred() {
        openPage();
        this.enterTextIntoInputLogin(TestData.INVALID_LOGIN);
        this.enterTextIntoInputPassword(TestData.INVALID_PASSWORD);
        clickOnButtonSighIn();
    }

    public void checkIsInputLoginAndPasswordNotVisible(){
        checkIsElementNotVisible(inputUserName);
        checkIsElementNotVisible(inputPassword);
    }
}
