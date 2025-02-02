package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.*;

public class HeaderForUserElement extends CommonActionsWithElements {
  @FindBy(xpath = "//a[@class='mr-2']")
  private WebElement buttonMyProfile;

  @FindBy(xpath = "//button[text()='Sign Out']")
  private WebElement buttonSignOut;


  @FindBy(xpath = "//a[@href='/create-post']")
  private WebElement buttonCreatePost;

  public HeaderForUserElement(WebDriver webDriver) {
    super(webDriver);
  }

  public MyProfilePage clickOnButtonMyProfile() {
    clickOnElement(buttonMyProfile);
    return new MyProfilePage(webDriver);
  }

  public HeaderForUserElement checkIsButtonSignOutVisible() {
//    Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    checkIsElementVisible(buttonSignOut);
    return this;
  }

  public CreareNewPostPage clickOnButtonCreatePost() {
    clickOnElement(buttonCreatePost);
    return new CreareNewPostPage(webDriver);
  }

  public boolean isButtonSignOutVisible() {
    return isElementVisible(buttonSignOut);
  }

  public HeaderForUserElement checkIsButtonCreatePostVisible() {
    checkIsElementVisible(buttonCreatePost);
    return this;
  }

  public HeaderForUserElement checkIsSignOutButtonNotVisible() {
    checkIsElementNotVisible(buttonSignOut);
    return this;
  }
}
