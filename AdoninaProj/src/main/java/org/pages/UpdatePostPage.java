package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdatePostPage extends ParentPage {
  private Logger logger = Logger.getLogger(getClass());

  @FindBy(xpath = ".//button[@class='btn btn-primary']")
  private WebElement buttonSaveUpdates;

  @FindBy(xpath = ".//input[@id='post-title']")
  private WebElement inputTitle;

  @FindBy(xpath = ".//div[contains(text(), 'Post')]")
  private WebElement successMessage;

  public UpdatePostPage(WebDriver webDriver) {
    super(webDriver);
  }

  @Override
  protected String getRelativeUrl() {
    return "/";
  }

  public UpdatePostPage checkIsRedirectToUpdatePostPage() {
    webDriver.get(baseUrl);
    logger.info("Login Page was opened with url " + baseUrl);
    return this;
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
