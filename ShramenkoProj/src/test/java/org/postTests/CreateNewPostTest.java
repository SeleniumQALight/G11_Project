package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    @Test
    public void TR003_createNewPost() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle("Post from Alla")
                .enterTextIntoInputBody("Body of the post")
                .checkboxUniqueState("uncheck")
                .clickOnButtonSaveNewPost().checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkStateUniquePost();
    }

}
