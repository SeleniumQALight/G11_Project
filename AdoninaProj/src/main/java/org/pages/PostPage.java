package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class PostPage extends ParentPage {
  @FindBy(xpath = "//*[@class='alert alert-success text-center']")
  private WebElement successMessage;

  @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
  private WebElement buttonDeletePost;

  @FindBy(xpath = ".//p[text()='Is this post unique? : yes']")
  private WebElement uniquePostMessage;

  public PostPage(WebDriver webDriver) {
    super(webDriver);
  }

  public HeaderForUserElement getHeaderElement() {
    return new HeaderForUserElement(webDriver);
  }

  public PostPage checkIsRedirectToPostPage() {
    //TODO check current URL();
    return this;
  }

  public PostPage checkIsSuccessMessageDisplayed() {
    checkIsElementVisible(successMessage);
    return this;
  }

  public PostPage checkTextInSuccessMessage(String expectedText) {
    checkTextInElement(successMessage, expectedText);
    return this;
  }

  public MyProfilePage clicKOnDeleteButton() {
    clickOnElement(buttonDeletePost, "Delete post Button");
    return new MyProfilePage(webDriver);
  }

  public PostPage checkIfUniquePost() {
    checkIsElementVisible(uniquePostMessage);
    return this;
  }
}
