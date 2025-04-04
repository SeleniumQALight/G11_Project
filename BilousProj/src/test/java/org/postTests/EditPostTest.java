package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class EditPostTest extends BaseTest {

    final String POST_TITLE = "TR005_Bilous" + Utils_Custom.getDateAndTimeFormatted();
    final String POST_TITLE_EDITED = "TR005_Bilous EDITED" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TR005_editPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .getElementsForCreateEditPost().enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post")
                .setOnCheckBoxIsPrivatePost()
                .clickOnButtonSavePost()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectOnMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
                .clickOnPostWithTitle(POST_TITLE)
                .clickOnEditButton()
                .checkIsRedirectOnEditPostPage()
                .getElementsForCreateEditPost().enterTextIntoInputTitle(POST_TITLE_EDITED)
                .clickOnButtonSaveUpdatedPost()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.")
        ;
        pageProvider.getHomePage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectOnMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE_EDITED, 1);

    }
    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectOnMyProfilePage()
                .deletePostTillPresent(POST_TITLE)
                .deletePostTillPresent(POST_TITLE_EDITED)
        ;

    }
}
