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

  @FindBy(xpath = "//a[@href='#']")
  private WebElement buttonSearch;

  @FindBy(xpath = "//span[@data-original-title='Chat']")
  private WebElement buttonChat;


  public HeaderForUserElement(WebDriver webDriver) {
    super(webDriver);
  }

  public MyProfilePage clickOnButtonMyProfile() {
    clickOnElement(buttonMyProfile);
    return new MyProfilePage(webDriver);
  }

  public HeaderForUserElement checkIsMyProfileButtonVisible() {
    checkIsElementVisible(buttonMyProfile);
    return this;
  }

  public HeaderForUserElement checkIsMyProfileButtonNotVisible() {
    checkIsElementNotVisible(buttonMyProfile);
    return this;
  }

  public HeaderForUserElement checkIsButtonSignOutVisible() {
//    Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    checkIsElementVisible(buttonSignOut);
    return this;
  }

  public LoginPage clickOnButtonSignOut() {
    clickOnElement(buttonSignOut);
    return new LoginPage(webDriver);
  }

  public HeaderForUserElement checkIsButtonSignOutNotVisible() {
//    Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    checkIsElementNotVisible(buttonSignOut);
    return this;
  }

  public CreateNewPostPage clickOnButtonCreatePost() {
    clickOnElement(buttonCreatePost);
    return new CreateNewPostPage(webDriver);
  }

  public boolean isButtonSignOutVisible() {
    return isElementVisible(buttonSignOut);
  }

  public HeaderForUserElement checkIsButtonCreatePostVisible() {
    checkIsElementVisible(buttonCreatePost);
    return this;
  }

  public HeaderForUserElement checkIsButtonCreatePostNotVisible() {
    checkIsElementNotVisible(buttonCreatePost);
    return this;
  }

  public HeaderForUserElement checkIsSignOutButtonNotVisible() {
    checkIsElementNotVisible(buttonSignOut);
    return this;
  }

  public HeaderForUserElement checkIsSearchButtonVisible() {
    checkIsElementVisible(buttonSearch);
    return this;
  }

  public HeaderForUserElement checkIsSearchButtonNotVisible() {
    checkIsElementNotVisible(buttonSearch);
    return this;
  }

  public HeaderForUserElement checkIsChatButtonVisible() {
    checkIsElementVisible(buttonChat);
    return this;
  }

  public HeaderForUserElement checkIsChatButtonNotVisible() {
    checkIsElementNotVisible(buttonChat);
    return this;
  }

  public HeaderForUserElement checkAllElementsInHeaderVisible() {
    this.checkIsSearchButtonVisible();
    this.checkIsChatButtonVisible();
    this.checkIsMyProfileButtonVisible();
    this.checkIsButtonCreatePostVisible();
    this.isButtonSignOutVisible();
    return new HeaderForUserElement(webDriver);
  }

  public HeaderForUserElement checkElementsInHeaderNotVisible() {
    this.checkIsSearchButtonNotVisible();
    this.checkIsChatButtonNotVisible();
    this.checkIsMyProfileButtonNotVisible();
    this.checkIsButtonCreatePostNotVisible();
    this.checkIsChatButtonNotVisible();
    return new HeaderForUserElement(webDriver);
  }

}
