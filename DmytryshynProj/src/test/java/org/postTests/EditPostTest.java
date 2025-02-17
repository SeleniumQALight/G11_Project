package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.utils.Utils_Custom;

public class EditPostTest extends BaseTest {
    // GUID = 1b1b1b1b-1b1b-1b1b-1b1b-1b1b1b1b1b1b

    final String POST_TITLE = "TR005_Mariana_" + Utils_Custom.getDateAndTimeFormatted();
    final String POST_TITLE_EDIT = "TR005_Mariana_Edited_" + Utils_Custom.getDateAndTimeFormatted();

    @Before
    public void createNewPost()  {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .cheskIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
    }

    @Test
    public void TR005_editPost() {
        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
                .clickOnPostWithTitle(POST_TITLE)
                .checkIsRedirectToPostPage()
                .clickOnEditButton();

        pageProvider.getEditPostPage()
                .checkIsRedirectToEditPostPage()
                .enterNewTextIntoInputTitle(POST_TITLE_EDIT)
                .clickOnSaveUpdates()
                .checkTextInSuccessMessage("Post successfully updated.");

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE_EDIT, 1);
    }


        @After
        public void deletePosts() {
            pageProvider.getHomePage()
                    .openHomePageAndLoginIfNeed()
                    .getHeaderElement().clickOnMyProfileButton()
                    .checkIsRedirectToMyProfilePage()
                    .deletePostsTillPresent(POST_TITLE_EDIT)
                    .deletePostsTillPresent(POST_TITLE)
            ;

    }

}
