package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateAndEditNewPost extends BaseTest {
    final String POST_TITLE = "TR003_Bezhevets" + Utils_Custom.getDateAndTimeFormatted();
    final String EDITED_POST_TITLE = "TR003_Bezhevets(EDITED)" + Utils_Custom.getDateAndTimeFormatted();


    @Test
    public void TR003_createAndEditNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .selectValueInDropDownAccess("One Person")
                .enterTextIntoInputBody("Body of the post")
                .setCheckboxWithState("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextThisPostWasWrittenIsVisible("One Person")
                .checkIsPostIsUnique()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnEditPostButton()
                .checkIsRedirectToEditPostPage()
                .enterNewTextIntoInputTitle(EDITED_POST_TITLE)
                .clickOnSaveUpdatedPostButton()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.");

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton().checkIsRedirectToMyProfilePage().checkPostWithTitleIsPresent(EDITED_POST_TITLE, 1);
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
                .deletePostsTillPresent(EDITED_POST_TITLE)

        ;
    }
}
