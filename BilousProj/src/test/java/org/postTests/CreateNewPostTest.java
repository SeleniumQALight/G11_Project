package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    // GUID
    final String POST_TITLE = "TR003_Bilous" + Utils_Custom.getDateAndTimeFormatted();


    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post")
                .setOnCheckBoxIsPrivatePost()
                .clickOnButtonSavePost()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

        ;

        pageProvider.getPostPage().getHeaderElement().clickOnButtomMyProfile()
                .checkIsRedirectOnMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;
    }
}
