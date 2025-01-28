package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    @Test
    public void TR003_createNewPost() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle("Title of the post from Alla")
                .enterTextIntoInputBody("Body of the post")
                .clickOnButtonSaveNewPost().checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
        ;
    }

}
