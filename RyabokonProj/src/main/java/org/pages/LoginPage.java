package org.pages;

import org.apache.log4j.Logger;
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

    public void openPage() {
        String baseUrl = "https://aqa-complexapp.onrender.com";
        webDriver.get(baseUrl);
        logger.info("Login Page was opened with url " + baseUrl);

    }

    public void enterTextIntoInputLogin(String login) {

      //  WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info(login + "qaauto was input UserName");

       //
    }

    public void enterTextIntoInputPassword(String password){
       // WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
         inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info(password + "password was input into input password");


    }

    public void clickOnButtonSignIn(){
      //  webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
       buttonSignIn.click();
        logger.info("Button SignIn was clicked");
    }
}
