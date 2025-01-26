package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred().checkIsRedirectOnHomePage().
                clickOnButtonCreatePost().checkIsRedirectOnCreateNewPostPage().enterTextIntoInputTitle("Title Ira").
                enterTextIntoInputBody("Body").clickOnButtonSaveNewPost().checkIsRedirectOnPostPage().checkIsSuccessMessageDisplayed().checkTextInSuccessMessage("New post successfully created.");
    }
}
