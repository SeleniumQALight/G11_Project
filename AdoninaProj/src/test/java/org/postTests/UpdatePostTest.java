package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.utils.Utils_Custom;


public class UpdatePostTest extends BaseTest {

  final String POST_TITLE = "TR005_adoniVA_" + Utils_Custom.getDateAndTimeFormatted();
  final String POST_TITLE_2 = "TR005_adoniVA_2" + Utils_Custom.getDateAndTimeFormatted();

  @Test
  public void TR005_UpdatePost() {
    pageProvider.getPostPage().clickOnEditeButton()
    ;
    pageProvider.getUpdatePostPage()
            .enterUpdateTextIntoInputTitle(POST_TITLE_2)
            .clickOnButtonSaveUpdates()
            .checkTextInSuccessMessage("Post successfully updated.")
    ;
    pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
            .checkIsRedirectToMyProfilePage()
            .checkPostWithTitleIsPresent(POST_TITLE_2, 1)
    ;

  }

  @Before
  public void createNewPost() {
    pageProvider.getLoginPage()
            .openLoginPageAndFillLoginFormWithValidCred()
            .checkIsRedirectOnHomePage();
    pageProvider.getHomePage().getHeaderElement().clickOnButtonCreatePost()
            .checkIsRedirectToCreateNewPostPage()
            .enterTextIntoInputTitle(POST_TITLE)
            .enterTextIntoInputBody("Body of the post")
            .clickOnButtonSaveNewPost()
            .checkIsRedirectToPostPage()
            .checkIsSuccessMessageDisplayed()
            .checkTextInSuccessMessage("New post successfully created.")
    ;
  }

  @After
  public void deletePost() {
    pageProvider.getHomePage()
            .openHomePageAndLoginIfNeeded()
            .getHeaderElement().clickOnButtonMyProfile()
            .checkIsRedirectToMyProfilePage()
            .deletePostsTitlePresent(POST_TITLE_2)
            .deletePostsTitlePresent(POST_TITLE)
    ;
  }
}
