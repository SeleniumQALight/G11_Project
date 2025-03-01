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

  @FindBy(xpath = "//p[text()='Is this post unique? : yes']")
  private WebElement uniquePostMessage;

  @FindBy(xpath = "//div[not(@*)]")
  private WebElement messageIsUniqueCheckbox;

  @FindBy(xpath = ".//a[@class='text-primary mr-2']")
  private WebElement buttonEditePost;

  private String locatorForTextThisPostWasWritten = "//*[contains(text(),'%s')]";

  public PostPage(WebDriver webDriver) {
    super(webDriver);
  }

  @Override
  protected String getRelativeUrl() {
    return "/post/[a-zA-Z0-9]*";
  }

  public HeaderForUserElement getHeaderElement() {
    return new HeaderForUserElement(webDriver);
  }

  public PostPage checkIsRedirectToPostPage() {
    checkUrlWithPattern();
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

  public PostPage checkIsPostUniqueCheckboxChecked(String expectedMessageText) {
    checkTextInElement(messageIsUniqueCheckbox, "Is this post unique? : " + expectedMessageText);
    return this;
  }

  public MyProfilePage clickOnEditeButton() {
    clickOnElement(buttonEditePost, "Edite post Button");
    return new MyProfilePage(webDriver);
  }

  public PostPage checkTextThisPostWasWrittenIsVisible(String text) {
    checkIsElementVisible(String.format(locatorForTextThisPostWasWritten, text));
    return this;
  }
}
