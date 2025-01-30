package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreareNewPostPage;
import org.pages.MyProfilePage;

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

  public void checkIsButtonSignOutVisible() {
//    Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    checkIsElementVisible(buttonSignOut);
  }

  public CreareNewPostPage clickOnButtonCreatePost() {
    clickOnElement(buttonCreatePost);
    return new CreareNewPostPage(webDriver);
  }
}
