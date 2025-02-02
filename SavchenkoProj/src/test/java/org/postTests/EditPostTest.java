package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class EditPostTest extends BaseTest {
    final String POST_TITLE_UNIQUE = "T0005_khasanova_" + Utils_Custom.getDateAndTimeFormatted();
    final String POST_TITLE_EDITED = "T0005_khasanova_edited_" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void T0005_editPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForUserElement()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE_UNIQUE)
                .enterTextIntoInputBody("Body of the post")
                .setNeededStateToPostUniqueCheckBox("Check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage();
                pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .clickOnPostWithTitle(POST_TITLE_UNIQUE)
                .checkIsRedirectToPostPage()
                .clickOnEditPostButton()
                .checkIsRedirectToEditPostPage();
        pageProvider.getCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE_EDITED);

        pageProvider.getEditPostPage().clickOnSaveUpdatesButton()
                .checkIsSuccessUpdateMessageDisplayed()
                .checkTextInSuccessUpdateMessage("Post successfully updated.");

        pageProvider.getHomePage().getHeaderForUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitlePresent(POST_TITLE_EDITED, 1);
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage().openHomePageAndLoginIfNeed()
                .getHeaderForUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(POST_TITLE_EDITED);
        pageProvider.getMyProfilePage().checkIsRedirectToProfilePage()
                .deletePostsTillPresent(POST_TITLE_UNIQUE);
    }
}
