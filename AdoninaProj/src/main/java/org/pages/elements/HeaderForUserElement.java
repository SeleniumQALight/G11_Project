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

  @FindBy(xpath = "//input[@placeholder='Username']")
  private WebElement inputUserName;

  @FindBy(xpath = "//input[@placeholder='Password']")
  private WebElement inputPassword;

  @FindBy(xpath = "//button[text()='Sign In']")
  private WebElement buttonSignIn;

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

  public CreateNewPostPage clickOnButtonSignOut() {
    clickOnElement(buttonSignOut);
    return new CreateNewPostPage(webDriver);
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

  public HeaderForUserElement checkIsInputLoginVisible() {
    checkIsElementVisible(inputUserName);
    return this;
  }

  public HeaderForUserElement checkIsInputPasswordVisible() {
    checkIsElementVisible(inputPassword);
    return this;
  }

  public HeaderForUserElement checkIsButtonSignInVisible() {
    checkIsElementVisible(buttonSignIn);
    return this;
  }

  public HeaderForUserElement checkAllElementsOnHomePageInHeaderVisible() {
    this.checkIsSearchButtonVisible();
    this.checkIsChatButtonVisible();
    this.checkIsMyProfileButtonVisible();
    this.checkIsButtonCreatePostVisible();
    this.isButtonSignOutVisible();
    return new HeaderForUserElement(webDriver);
  }

  public HeaderForUserElement checkElementsOnLoginPageInHeaderNotVisible() {
    this.checkIsSearchButtonNotVisible();
    this.checkIsChatButtonNotVisible();
    this.checkIsMyProfileButtonNotVisible();
    this.checkIsButtonCreatePostNotVisible();
    this.checkIsChatButtonNotVisible();
    return new HeaderForUserElement(webDriver);
  }

  public HeaderForUserElement checkAllElementsOnLoginPageInHeaderVisible() {
    this.checkIsInputLoginVisible();
    this.checkIsInputPasswordVisible();
    this.checkIsButtonSignInVisible();
    return new HeaderForUserElement(webDriver);
  }
}
