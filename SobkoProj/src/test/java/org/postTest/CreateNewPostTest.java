package org.postTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndLoginFormWithValidCreds()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .enterTextIntoInputTitle("Title Sobko")
                .enterTextIntoInputBody("Body YS")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

        ;
    }
}
