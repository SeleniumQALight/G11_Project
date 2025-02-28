package org.pages.elements;

import io.qameta.allure.Step;
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

  @Step
  public MyProfilePage clickOnButtonMyProfile() {
    clickOnElement(buttonMyProfile);
    return new MyProfilePage(webDriver);
  }

  @Step
  public HeaderForUserElement checkIsMyProfileButtonVisible() {
    checkIsElementVisible(buttonMyProfile);
    return this;
  }

  @Step
  public HeaderForUserElement checkIsMyProfileButtonNotVisible() {
    checkIsElementNotVisible(buttonMyProfile);
    return this;
  }

  @Step
  public HeaderForUserElement checkIsButtonSignOutVisible() {
//    Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    checkIsElementVisible(buttonSignOut);
    return this;
  }

  @Step
  public LoginPage clickOnButtonSignOut() {
    clickOnElement(buttonSignOut);
    return new LoginPage(webDriver);
  }

  @Step
  public HeaderForUserElement checkIsButtonSignOutNotVisible() {
//    Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    checkIsElementNotVisible(buttonSignOut);
    return this;
  }

  @Step
  public CreateNewPostPage clickOnButtonCreatePost() {
    clickOnElement(buttonCreatePost);
    return new CreateNewPostPage(webDriver);
  }

  @Step
  public boolean isButtonSignOutVisible() {
    return isElementVisible(buttonSignOut);
  }

  @Step
  public HeaderForUserElement checkIsButtonCreatePostVisible() {
    checkIsElementVisible(buttonCreatePost);
    return this;
  }

  @Step
  public HeaderForUserElement checkIsButtonCreatePostNotVisible() {
    checkIsElementNotVisible(buttonCreatePost);
    return this;
  }

  @Step
  public HeaderForUserElement checkIsSignOutButtonNotVisible() {
    checkIsElementNotVisible(buttonSignOut);
    return this;
  }

  @Step
  public HeaderForUserElement checkIsSearchButtonVisible() {
    checkIsElementVisible(buttonSearch);
    return this;
  }

  @Step
  public HeaderForUserElement checkIsSearchButtonNotVisible() {
    checkIsElementNotVisible(buttonSearch);
    return this;
  }

  @Step
  public HeaderForUserElement checkIsChatButtonVisible() {
    checkIsElementVisible(buttonChat);
    return this;
  }

  @Step
  public HeaderForUserElement checkIsChatButtonNotVisible() {
    checkIsElementNotVisible(buttonChat);
    return this;
  }

  @Step
  public HeaderForUserElement checkAllElementsInHeaderVisible() {
    this.checkIsSearchButtonVisible();
    this.checkIsChatButtonVisible();
    this.checkIsMyProfileButtonVisible();
    this.checkIsButtonCreatePostVisible();
    this.isButtonSignOutVisible();
    return new HeaderForUserElement(webDriver);
  }

  @Step
  public HeaderForUserElement checkElementsInHeaderNotVisible() {
    this.checkIsSearchButtonNotVisible();
    this.checkIsChatButtonNotVisible();
    this.checkIsMyProfileButtonNotVisible();
    this.checkIsButtonCreatePostNotVisible();
    this.checkIsChatButtonNotVisible();
    return new HeaderForUserElement(webDriver);
  }

}
