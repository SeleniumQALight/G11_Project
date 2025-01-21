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
        clearAndEnterTextIntoElement(inputUserName, login);

    }

    public void enterTextIntoInputPassword(String password) {
        //WebElement inputPassword = webdriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + "was inputted into input password");
        clearAndEnterTextIntoElement(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(clickSignIn);
    }

}

