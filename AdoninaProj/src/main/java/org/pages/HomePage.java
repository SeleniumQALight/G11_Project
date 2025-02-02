package org.pages;


import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForUserElement;

public class HomePage extends ParentPage {
  private Logger logger = Logger.getLogger(getClass());


  @FindBy(xpath = "//input[@placeholder='Username']")
  private WebElement inputUsername;

  @FindBy(xpath = "//input[@placeholder='Password']")
  private WebElement inputPassword;

  public HomePage(WebDriver webDriver) {
    super(webDriver);
  }

  public HeaderForUserElement getHeaderElement() {
    return new HeaderForUserElement(webDriver);
  public HomePage checkIsButtonSignOutVisible() {
//    Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    checkIsElementVisible(buttonSignOut);
    return this;
  }

  public HomePage checkIsRedirectOnHomePage() {
    getHeaderElement().checkIsButtonSignOutVisible();
    //TODO check current URL
    return this;
  }

  public HomePage openHomePageAndLoginIfNeeded() {
    LoginPage loginPage = new LoginPage(webDriver);
    loginPage.openPage();
    if (getHeaderElement().isButtonSignOutVisible()) {
      logger.info("User is already logged in");
    } else {
      loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN);
      loginPage.enterTextIntoInputPassw0rd(TestData.VALID_PASSWORD);
      loginPage.clickOnButtonSignIn();
      checkIsRedirectOnHomePage();
      logger.info("User was logged in");
    }
    return this;
  }


  public HomePage checkIsButtonCreatePostVisible() {
    checkIsElementVisible(buttonCreatePost);
    return this;
  }

  public HomePage checkIsInputUsernameNotVisible() {
    checkIsElementNotVisible(inputUsername);
    return this;
  }

  public HomePage checkIsInputPasswordNotVisible() {
    checkIsElementNotVisible(inputPassword);
    return this;
  }
}
