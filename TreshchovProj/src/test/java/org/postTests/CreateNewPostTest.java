package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
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
                selectValueInDD("One Person").
                enterTextIntoInputBody("test body").
                clickOnButtonSavePost().
                checkIsRedirectToPostPage().
                checkIsAlertSuccessPresent().
                checkTextThisPostWasWrittenIsVisible("One Person").
                checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage().
                getHeaderElement().
                clickOnButtonMyProfile().checkIsRedirectToMyProfilePage().checkPostWithTitlePresent(TITLE, 1)

        ;
    }

    @After
    public void deletePosts(){
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded().getHeaderElement().clickOnButtonMyProfile().checkIsRedirectToMyProfilePage().deletePostsTillPresent(TITLE);

    }

}
