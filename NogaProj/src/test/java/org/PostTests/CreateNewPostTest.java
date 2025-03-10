package org.PostTests;

import org.baseTest.BaseTest;
import org.junit.After;
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
                .selectTextInDropDownAccess("One Person")
                .enterTextIntoBody("Body AN")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextThisPostWasWrittenIsVisible("One Person")
        ;

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1);

    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement()
                .clickOnMyProfileButton()
                .deletePostTillPresent(POST_TITLE);

    }
}
