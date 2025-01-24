package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage().
                openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .clickOnButtonCreatePost().
                checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle("Title of the post Horbovskyi")
                .enterTextIntoInputBody("Body of the post Horbovskyi")
                .clickOnButtonSavePost()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

    }

}
