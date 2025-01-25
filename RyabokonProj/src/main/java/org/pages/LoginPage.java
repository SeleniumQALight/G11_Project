package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{

    private Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password'] ")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In'] ")
    private WebElement buttonSignIn;

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
       /* WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys(login);
        logger.info(login + "was input UserName");*  !!!!!!!! we have described it in @FindBy*/
        clearAndEnterTextIntoElement(inputUserName, login);
return this;

    }

    public LoginPage enterTextIntoInputPassword(String password){
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn(){
        //buttonSignIn.click(); ** before we have created method CommonActionsWithElements

        clickOnElement(buttonSignIn);
    }

    public HomePage openLoginPageAndFillFormWithValidCred() {
        openPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN);
        this.enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}
