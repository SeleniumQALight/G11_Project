package org.postTests;

import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //GUID = 1b1b1b1b-1b1b-1b1b-1b1b-1b1b1b1b1b1b

    final String POST_TITLE = "T0003_khasanova_" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void T0003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForUserElement()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .selectValueInDropdownAccess("One Person")
                .enterTextIntoInputBody("Body of the post")
                .setNeededStateToPostUniqueCheckBox("Check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextThisPostWasWrittenIsVisible("One Person")
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUniqueCheckboxChecked("Is this post unique? : yes")
        ;

        pageProvider.getPostPage().getHeaderForUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1);
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded()
                .getHeaderForUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }
}
