package org.postTest;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

import static org.postTest.CreateNewPostTest.*;

public class EditPost extends BaseTest {

    final String CHANGED_POST_TITLE = "TR003 *** Ryabokon CHANGED" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TR004_editPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body**** of the ****post")
                .clickOnSaveNewPostButton()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.");

        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
                .clickOnPostWithTitle(POST_TITLE);

        pageProvider.getPostPage().clickOnEditPostButton()
                .enterTextIntoInputTitle(CHANGED_POST_TITLE)
                .clickOnSaveEditedPostButton()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.");

        pageProvider.getHomePage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(CHANGED_POST_TITLE, 1)
                .deletePostsTillPresent(CHANGED_POST_TITLE);
    }

    @After
    public void deleteEditedPost() {
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(CHANGED_POST_TITLE)
                .deletePostsTillPresent(POST_TITLE);

    }
}