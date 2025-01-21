package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;
    // WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));  //адреса до єлемента

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSighIn;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com";
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
    }

    public void enterTextIntoInputLogin(String login) {
//        inputUserName.clear(); //почистити поле input
//        inputUserName.sendKeys(login);//посилаємо login
//        logger.info(login + " was inputted into input UserName");
        clearAndEnterTextIntoElement(inputUserName, login);
    }

    public void enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPassword, password);
    }

    public void clickOnButtonSighIn() {
        clickOnElement(buttonSighIn);
    }
}
