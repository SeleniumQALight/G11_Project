package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.utils.Utils_Custom;

public class EditPostTest extends BaseTest {

    final String POST_TITLE = "TR003_TanyaChe_" + Utils_Custom.getDateAndTimeFormatted();
    final String POST_TITLE_EDIT = "TR003_TanyaChe_Edit" + Utils_Custom.getDateAndTimeFormatted();

    @Before
    public void createNewPost()  {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body from Che")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSussesMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

    }
    @Test
    public void TR001_editPost() {
        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .checkIsPostWithTitleIsPresent(POST_TITLE, 1)
                .openPost(POST_TITLE)
                .checkIsRedirectToPostPage()
                .clickOnEditPostButton();

        pageProvider.getEditPostPage()
                .checkIsRedirectToEditPostPage()
                .enterNewTextIntoInputTitle(POST_TITLE_EDIT)
                .clickOnButtonSaveUpdatedPost()
                .checkTextInSuccessMessage("Post successfully updated.");

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .checkIsPostWithTitleIsPresent(POST_TITLE_EDIT, 1);
    }


    @After
    public void deletePosts() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(POST_TITLE_EDIT)
                .deletePostsTillPresent(POST_TITLE)
        ;

    }
}
