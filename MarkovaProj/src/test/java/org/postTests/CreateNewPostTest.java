package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.pages.PostPage;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred().checkIsRedirectOnHomePage().
                clickOnButtonCreatePost().checkIsRedirectOnCreateNewPostPage().enterTextIntoInputTitle("Title Ira").
                enterTextIntoInputBody("Body").
                clickOnButtonSaveNewPost().checkIsRedirectOnPostPage().checkIsSuccessMessageDisplayed().
                checkTextInSuccessMessage("New post successfully created.");
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded().clickOnButtonMyProfile().checkIsRedirectToProfilePage().
                deletePostsTillPresent("Title Ira");

    }
}
