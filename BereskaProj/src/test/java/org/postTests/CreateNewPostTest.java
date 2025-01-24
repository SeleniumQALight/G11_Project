package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle("Title Bereska")
                .enterTextIntoInputBody("Body")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
    }
}
