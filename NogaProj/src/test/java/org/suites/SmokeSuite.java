package org.suites;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTest.LoginTestWithPageObject;
import org.pages.CreateNewPostPage;
import org.registrationTests.ValidationMessageTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(org.categories.SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        ValidationMessageTest.class,
        CreateNewPostPage.class
})
public class SmokeSuite {

}
