package org.PostTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void T0003_CreateNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageandFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoTitle("Title AN")
                .enterTextIntoBody("Body AN")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;


    }
}
