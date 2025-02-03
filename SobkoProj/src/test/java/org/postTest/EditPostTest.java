package org.postTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.utils.Utils_Custom;
import static org.data.TestData.*;

public class EditPostTest extends BaseTest {

    final String POST_TITLE = "Post to be edited " + Utils_Custom.getDateAndTimeFormatted();
    final String EDITED_POST_TITLE = "EDITED_POST_TiTLE " + Utils_Custom.getDateAndTimeFormatted();

    @Before
    public void createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndLoginFormWithValidCreds()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body YS")
                .setUniquePostCheckboxState(CHECK)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsPostUniqueCorrectState(YES)
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.");
    }

    @Test
    public void TR001_editPost() {
        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
                .openPost(POST_TITLE)
                .checkIsRedirectToPostPage()
                .clickOnEditButton()
                .enterTextIntoInputTitle(EDITED_POST_TITLE)
                .clickOnSaveUpdatedButton()
                .checkTextInUpdateMessage("Post successfully updated.");

        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(EDITED_POST_TITLE, 1);
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(EDITED_POST_TITLE)
                .deletePostTillPresent(POST_TITLE);
    }
}
