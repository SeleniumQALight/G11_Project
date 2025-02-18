package org.postTest;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //GUID = 1b1b1b1b-1b1b-1b1b-1b1b-1b1b1b1b1b1b

    public static final String postTitle = "TR003 *** Ryabokon" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(postTitle)
                .selectValueInDropDownAccess("One Person")
                .enterTextIntoInputBody("Body**** of the ****post")
                .setCheckboxState()
                .clickOnSaveNewPostButton()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextThisPostWasWrittenIsVisible("One Person")
                .checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(postTitle, 1)
        ;
    }





    @After
    public void deletePost() {
pageProvider.getHomePage().openHomePageAndLoginIfNeeded()
        .getHeaderElement().clickOnButtonMyProfile()
        .checkIsRedirectToProfilePage()
        .deletePostsTillPresent(postTitle)

        ;

    }

}
