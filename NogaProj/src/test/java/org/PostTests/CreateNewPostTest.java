package org.PostTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "T0003_noga" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void T0003_CreateNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageandFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeaderElement()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoTitle(POST_TITLE)
                .enterTextIntoBody("Body AN")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1);

    }
}
