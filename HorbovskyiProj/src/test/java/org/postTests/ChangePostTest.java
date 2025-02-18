package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class ChangePostTest extends BaseTest {


    @Test
    public void T0004_changePost() {
        //sign in
        pageProvider.getLoginPage().
                openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                // create post
                .createNewPostWithUniqueTitle(CreateNewPostTest.POST_TITLE, "Body of the post Horbovskyi");


    }
}
