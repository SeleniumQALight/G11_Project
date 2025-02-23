package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

import static org.data.TestData.POST_TITLE;

public class CreateNewPostTest extends BaseTest {

    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage().
                openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .selectValueInDropdownAccess("One Person")
                .enterTextIntoInputBody("Body of the post Horbovskyi")
                .selectUniquePostCheckbox("check")
                .clickOnButtonSavePost()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextThisPostWasWrittenIsVisible("One Person")
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIfUniquePost().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkIsPostWithTitleWereAdded(POST_TITLE, 1)
        ;

    }

    @After
    public void deletePost() {
        pageProvider.getHomePage().openHomePageIfNeeded().
                getHeaderElement().clickOnButtonMyProfile().checkIsRedirectToMyProfilePage()
                .deletePostWhilePresent(POST_TITLE)
        ;
    }

}
