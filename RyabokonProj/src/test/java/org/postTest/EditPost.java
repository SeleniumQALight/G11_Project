package org.postTest;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

import static org.postTest.CreateNewPostTest.*;

public class EditPost extends BaseTest {

    final String changedPostTitle = "TR004 *** Ryabokon CHANGED" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TR004_editPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(postTitle)
                .enterTextIntoInputBody("Body**** of the ****post")
                .clickOnSaveNewPostButton()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.");

        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(postTitle, 1)
                .clickOnPostWithTitle(postTitle);

        pageProvider.getPostPage().clickOnEditPostButton();
        pageProvider.getEditPostPage()
                .enterTextIntoInputTitle(changedPostTitle)
                .clickOnSaveEditedPostButton()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.");

        pageProvider.getHomePage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(changedPostTitle, 1)
                .deletePostsTillPresent(changedPostTitle);
    }

    @After
    public void deleteEditedPost() {
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(changedPostTitle)
                .deletePostsTillPresent(postTitle);

    }
}