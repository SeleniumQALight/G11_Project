package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //GUID =

    final String TITLE = "Treshchov Test" + Utils_Custom.getDateAndTimeFormatted();
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage().
                openLoginAndFillLoginFormWithValidData().
                checkIsRedirectToHomePage().
                getHeaderElement().
                clickOnButtonCreatePost().
                checkIsRedirectToCreateNewPostPage().
                enterTextIntoInputTitle(TITLE).
                enterTextIntoInputBody("test body").
                clickOnButtonSavePost().
                checkIsRedirectToPostPage().
                CheckIsAlertSuccessPresent().
                checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage().
                getHeaderElement().
                clickOnButtonMyProfile().checkIsRedirectToMyProfilePage().checkPostWithTitlePresent(TITLE, 1)

        ;
    }

}
