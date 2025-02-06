package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.utils.Utils_Custom;

public class EditPostTest extends BaseTest {
    final String POST_TITLE_2 = "TR004_Shramenko_" + Utils_Custom.getDateAndTimeFormatted();
    final String EDITED_POST_TITLE = "New TITLE " + POST_TITLE_2;


    @Before
    public void newPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE_2)
                .enterTextIntoInputBody("Body of the post")
                .clickOnButtonSaveNewPost().checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE_2, 1) //працює поки наш пост тільки один
        ;
    }

    @Test
    public void T004_editPostTest() {
        pageProvider.getMyProfilePage()
                .editPostsTillPresent(POST_TITLE_2, EDITED_POST_TITLE)
                .checkPostWithTitleIsPresent(EDITED_POST_TITLE, 1)
        ;
    }

    @After
    public void deleteAllPostsAfterTest() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(EDITED_POST_TITLE)
                .deletePostsTillPresent(POST_TITLE_2)
        ;
    }


}
