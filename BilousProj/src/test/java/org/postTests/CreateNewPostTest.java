package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
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
                .selectValueInDropdownAccess("One Person")
                .enterTextIntoInputBody("Body of the post")
                .setOnCheckBoxIsPrivatePost()
                .clickOnButtonSavePost()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextThisPostWasWrittenIsVisible("One Person")
                .checkTextInSuccessMessage("New post successfully created.")

        ;

        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectOnMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;
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
