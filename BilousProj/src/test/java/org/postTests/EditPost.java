package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class EditPost extends BaseTest {

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
//                .enterTextIntoInputTitle(POST_TITLE + "_edited")
//                .enterTextIntoInputBody("Edited body of the post")
//                .clickOnButtonSavePost()
//                .checkIsRedirectOnPostPage()
//                .checkIsSuccessMessageDisplayed()
//                .checkTextInSuccessMessage("Post successfully updated.")




        ;
//
//        pageProvider.getMyProfilePage().clickOnPostWithTitle(POST_TITLE)
//                .checkIsRedirectOnPostPage()
//                .clickOnButtonEditPost()
//                .checkIsRedirectOnEditPostPage()
//                .enterTextIntoInputTitle(POST_TITLE + "_edited")
//                .enterTextIntoInputBody("Edited body of the post")
//                .clickOnButtonSavePost()
//                .checkIsRedirectOnPostPage()
//                .checkIsSuccessMessageDisplayed()
//                .checkTextInSuccessMessage("Post successfully updated.")
//        ;
//
//        pageProvider.getPostPage().getHeaderElement().clickOnButtomMyProfile()
//                .checkIsRedirectOnMyProfilePage()
//                .checkPostWithTitleIsPresent(POST_TITLE + "_edited", 1)
    }
    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectOnMyProfilePage()
                .deletePostTillPresent(POST_TITLE)
        ;

    }
}
