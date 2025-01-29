package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
//    GUID

    final String POST_TITLE = "TR003_TanyaChe_" + Utils_Custom.getDateAndTimeFormatted();
    @Test
    public void TR_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSussesMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

        ;

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .checkIsPostWithTitleIsPresent(POST_TITLE, 1)
        ;
    }
}
