package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage().
                openLoginAndFillLoginFormWithValidData().
                checkIsRedirectToHomePage().
                clickOnButtonCreatePost().
                checkIsRedirectToCreateNewPostPage().
                enterTextIntoInputTitle("Test123Treshchov").
                enterTextIntoInputBody("test body").
                clickOnButtonSavePost().
                checkIsRedirectToPostPage().
                CheckIsAlertSuccessPresent().
                checkTextInSuccessMessage("New post successfully created.")
        ;
    }

}
