package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //GUID = 1b1b1b1b-1b1b-1b1b-1b1b-1b1b1b1b1b1b
    //GUID гарантує унікальність, але не несе змісту


    //унікальність забезпечуємо за допомогою дати і часу
    final String POST_TITLE = "TR003_Shramenko_" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TR003_createNewPost() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post")
                .checkboxUniqueState("uncheck")
                .clickOnButtonSaveNewPost().checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkStateUniquePost();

        ;

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1) //працює поки наш пост тільки один
        ;
    }

    @After
    public void deletePosts() {

    }

}
