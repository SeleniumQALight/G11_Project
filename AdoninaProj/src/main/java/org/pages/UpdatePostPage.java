package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdatePostPage extends ParentPage {

  @FindBy(xpath = ".//button[@class='btn btn-primary']")
  private WebElement buttonSaveUpdates;

  @FindBy(xpath = ".//input[@id='post-title']")
  private WebElement inputTitle;

  @FindBy(xpath = ".//div[contains(text(), 'Post')]")
  private WebElement successMessage;

  public UpdatePostPage(WebDriver webDriver) {
    super(webDriver);
  }

  public UpdatePostPage checkIsRedirectToUpdatePostPage() {
    //TODO check URL ();
    return null;
  }

  public UpdatePostPage enterUpdateTextIntoInputTitle(String title) {
    clearAndEnterTextIntoInput(inputTitle, title);
    return this;
  }

  public PostPage clickOnButtonSaveUpdates() {
    clickOnElement(buttonSaveUpdates);
    return new PostPage(webDriver);
  }

  public UpdatePostPage checkTextInSuccessMessage(String expectedText) {
    checkTextInElement(successMessage, expectedText);
    return this;
  }
}
