package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreareNewPostPage extends ParentPage {
  //  @FindBy(xpath = ".//input[@id='post-title']")
  @FindBy(name = "title")
  private WebElement inputTitle;

  @FindBy(id = "post-body") //"//textarea[@id='post-body']"
  private WebElement inputBody;

  @FindBy(xpath = "//button[@class='btn btn-primary']")
  private WebElement buttonSaveNewPost;

  @FindBy(xpath = "//input [@type='checkbox']")
  private WebElement checkboxIsPrivatePost;

  @FindBy(xpath = ".//select")
  private WebElement dropDownSelectCategory;

  public CreareNewPostPage(WebDriver webDriver) {
    super(webDriver);
  }

  @Override
  protected String getRelativeUrl() {
    return "/create-post";
  }

  //check is redirect to CreateNewPostPage
  public CreareNewPostPage checkIsRedirectToCreateNewPostPage() {
    checkUrlWithPattern();
    return this;
  }

  public CreareNewPostPage selectValueDDCategory(String valueForSelect) {
    selectValueInDD(dropDownSelectCategory, valueForSelect);
    return this;
  }

  public CreareNewPostPage enterTextIntoInputTitle(String title) {
    clearAndEnterTextIntoInput(inputTitle, title);
    return this;
  }

  public CreareNewPostPage enterTextIntoInputBody(String body) {
    clearAndEnterTextIntoInput(inputBody, body);
    return this;
  }

  public PostPage clickOnButtonSaveNewPost() {
    clickOnElement(buttonSaveNewPost);
    return new PostPage(webDriver);
  }

  public CreareNewPostPage setOnCheckBoxIsPrivatePost(String needState) {
    setCheckboxState(checkboxIsPrivatePost, needState);
    return this;
  }
}
