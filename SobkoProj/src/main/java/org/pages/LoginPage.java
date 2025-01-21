package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com/";
        webdriver.get(baseUrl);
        logger.info("Login Page was opened url " + baseUrl);
    }

    public void enterTextIntoInputLogin(String login) {

        //WebElement inputUserName = webdriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys(login);
        logger.info(login + "was inputted into input UserName");


    }

    public void enterTextIntoInputPassword(String password) {
        //WebElement inputPassword = webdriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info(password + "was inputted into input password");
    }

    public void clickOnButtonSignIn() {
       // WebElement clickButton = webdriver.findElement(By.xpath("//button[text()='Sign In']"));

        clickSignIn.click();
        logger.info("Button SignIn was clicked ");
    }
}

