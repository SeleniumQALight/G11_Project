package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TR003Horbovskyi" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage().
                openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle("Title of the post Horbovskyi")
                .enterTextIntoInputBody("Body of the post Horbovskyi")
                .clickOnButtonSavePost()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkIsPostWithTitleWereAdded(POST_TITLE, 1)
        ;

    }

}
