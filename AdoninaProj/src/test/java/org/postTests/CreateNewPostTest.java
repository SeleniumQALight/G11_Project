package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
  @Test
  public void TR003_CreateNewPost() {
    pageProvider.getLoginPage()
            .openLoginPageAndFillLoginFormWithValidCred()
            .checkIsRedirectOnHomePage()
            .clickOnButtonCreatePost()
            .checkIsRedirectToCreateNewPostPage()
            .enterTextIntoInputTitle("Title of the post VA")
            .enterTextIntoInputBody("Body of the post")
            .setOnCheckBoxIsPrivatePost()
            .clickOnButtonSaveNewPost()
            .checkIsRedirectToPostPage()
            .checkIsSuccessMessageDisplayed()
            .checkTextInSuccessMessage("New post successfully created.")
            ;
  }
}
