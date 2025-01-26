package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
//  private Logger logger = Logger.getLogger(getClass());

  @FindBy(xpath = "//button[text()='Sign Out']")
  private WebElement buttonSignOut;


  @FindBy(xpath = "//a[@href='/create-post']")
  private WebElement buttonCreatePost;

  public HomePage(WebDriver webDriver) {
    super(webDriver);
  }

  public void checkIsButtonSignOutVisible() {
//    Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    checkIsElementVisible(buttonSignOut);
  }

  public HomePage checkIsRedirectOnHomePage() {
    checkIsButtonSignOutVisible();
    //TODO check current URL
    return this;
  }

  public CreareNewPostPage clickOnButtonCreatePost() {
    clickOnElement(buttonCreatePost);
    return new CreareNewPostPage(webDriver);
  }

}
