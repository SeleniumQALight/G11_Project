package org.postTest;

import org.baseTest.BaseTest;
import org.utils.Utils_Custom;
import org.junit.Test;




public class CreateNewPostTest extends BaseTest {

    final String POST_TITLE = "Yurii Sobko post"+Utils_Custom.getDateAndTimeFormatted();
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndLoginFormWithValidCreds()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body YS")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

        ;

        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)

                  ;

    }
}
