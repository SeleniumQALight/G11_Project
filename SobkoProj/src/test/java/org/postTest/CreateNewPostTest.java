package org.postTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import static org.data.TestData.CHECK;
import static org.data.TestData.YES;


public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndLoginFormWithValidCreds()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .enterTextIntoInputTitle("Title Sobko")
                .enterTextIntoInputBody("Body YS")
                .setUniquePostCheckboxState(CHECK)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsPostUniqueCorrectState(YES)
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

        ;
    }
}
