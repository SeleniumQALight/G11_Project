package org.postTests;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.data.TestData.*;

public class ChangePostTest extends BaseTest {

    private final String POST_TITLE_EDITED = POST_TITLE + "123";

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
                .enterTextIntoInputTitle(POST_TITLE_EDITED)
                .clickOnButtonSaveUpdates()
                .checkIsSuccessMessageDisplayed()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkIsPostWithTitleWereAdded(POST_TITLE_EDITED, 1)
        ;


    }

    @After
    public void deletePost() {
        pageProvider.getHomePage().openHomePageIfNeeded().
                getHeaderElement().clickOnButtonMyProfile().checkIsRedirectToMyProfilePage()
                .deletePostWhilePresent(POST_TITLE_EDITED)
                .deletePostWhilePresent(POST_TITLE);
    }
}
