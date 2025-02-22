package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateAndEditPostTest extends BaseTest {
    final String TITLE = "Treshchov Test" + Utils_Custom.getDateAndTimeFormatted();
    final String TITLE_EDITED = "Treshchov Test Edited" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void HW4_CreateAndEditPost() {
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
                checkTextInSuccessMessage("New post successfully created.").
                getHeaderElement().
                clickOnButtonMyProfile().EditPostWithNewTitle(TITLE, TITLE_EDITED).checkPostWithTitlePresent(TITLE_EDITED, 1);
        ;
    }

    @After
    public void deletePosts(){
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded().getHeaderElement().clickOnButtonMyProfile().checkIsRedirectToMyProfilePage().deletePostsTillPresent(TITLE_EDITED);
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded().getHeaderElement().clickOnButtonMyProfile().checkIsRedirectToMyProfilePage().deletePostsTillPresent(TITLE);

    }

}
