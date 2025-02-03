package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
  // GUID = 1b3b3b3b-0b3b-4b3b-8b3b-2b3b3b3b3b3b

  final String POST_TITLE = "TR003_adonVA_" + Utils_Custom.getDateAndTimeFormatted();

  @Test
  public void TR003_CreateNewPost() {
    pageProvider.getLoginPage()
            .openLoginPageAndFillLoginFormWithValidCred()
            .checkIsRedirectOnHomePage()
            .getHeaderElement().clickOnButtonCreatePost()
            .checkIsRedirectToCreateNewPostPage()
            .enterTextIntoInputTitle(POST_TITLE)
            .enterTextIntoInputBody("Body of the post")
            .setOnCheckBoxIsPrivatePost("check")
            .clickOnButtonSaveNewPost()
            .checkIsRedirectToPostPage()
            .checkIsSuccessMessageDisplayed()
            .checkTextInSuccessMessage("New post successfully created.")
            .checkIfUniquePost().getHeaderElement().clickOnButtonMyProfile()
    ;

    pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
            .checkIsRedirectToMyProfilePage()
            .checkPostWithTitleIsPresent(POST_TITLE, 1)
    ;
  }

  @After
  public void deletePost() {
    pageProvider.getHomePage()
            .openHomePageAndLoginIfNeeded()
            .getHeaderElement().clickOnButtonMyProfile()
            .checkIsRedirectToMyProfilePage()
            .deletePostsTitlePresent(POST_TITLE)
    ;


  }

}
