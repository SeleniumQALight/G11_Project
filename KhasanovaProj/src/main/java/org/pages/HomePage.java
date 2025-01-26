package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getButtonSignOut() {
        return buttonSignOut;
    }

    public HomePage checkIsButtonSignOutVisible() {
//        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

//    private boolean isButtonSignOutVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            logger.info(state + " is element visible");
//            return state;
//        } catch (Exception e) {
//            logger.info("Element is not found");
//            return false;
//        }
//    }

    public HomePage checkIsRedirectToHomePage() {
        checkIsButtonSignOutVisible();
        //TODO check current URL
        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public HomePage checkIsUsernameInputInvisible() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.checkIsElementInvisible(loginPage.getInputUserName());
        return this;
    }

    public HomePage checkIsInputPasswordInvisible() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.checkIsElementInvisible(loginPage.getInputPassword());
        return this;
    }


}
