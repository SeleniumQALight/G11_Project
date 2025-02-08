package org.postTest;

import org.baseTest.BaseTest;
import org.junit.After;
import org.utils.Utils_Custom;
import org.junit.Test;
import static org.data.TestData.CHECK;
import static org.data.TestData.YES;





public class CreateNewPostTest extends BaseTest {

    final String POST_TITLE = "Yurii Sobko post"+Utils_Custom.getDateAndTimeFormatted();
    @Test
    public void TR001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndLoginFormWithValidCreds()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .enterTextIntoInputTitle(POST_TITLE)
                .selectValueInDropDownAccess("One Person")
                .enterTextIntoInputBody("Body YS")
                .setUniquePostCheckboxState(CHECK)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsPostUniqueCorrectState(YES)
                .checkIsSuccessMessageDisplayed()
                .checkTextThisPostWasWrittenAndVisible("One Person")
                .checkTextInSuccessMessage("New post successfully created.")

        ;

        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)

                  ;

    }
    @After
    public void deletePost() {
     pageProvider.getHomePage().openHomePageAndLoginIfNeeded()
             .getHeaderElement().clickOnButtonMyProfile()
             .checkIsRedirectToMyProfilePage()
             .deletePostTillPresent(POST_TITLE);

    }
}
