package org.postTests;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.data.TestData.*;

public class ChangePostTest extends BaseTest {

    @Before
    public void signInAndCreatePost() {
        pageProvider.getLoginPage().
                openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage().createNewPostWithUniqueTitle(TestData.POST_TITLE, "Body of the post Horbovskyi")
                .checkIsRedirectOnPostPage();
    }

    @Test
    public void T0004_changePost() {

        pageProvider.getPostPage()
                .clickOnEditButton()
                .enterTextIntoInputTitle(POST_TITLE + "123")
                .clickOnButtonSaveUpdates()
                .checkIsSuccessMessageDisplayed()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkIsPostWithTitleWereAdded(POST_TITLE + "123", 1)
        ;


    }

    @After
    public void deletePost() {
        pageProvider.getHomePage().openHomePageIfNeeded().
                getHeaderElement().clickOnButtonMyProfile().checkIsRedirectToMyProfilePage()
                .deletePostWhilePresent(POST_TITLE + "123")
                .deletePostWhilePresent(POST_TITLE);
    }
}
