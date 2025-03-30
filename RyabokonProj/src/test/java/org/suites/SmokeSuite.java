package org.suites;

import org.apiTests.ApiTests;
import org.categories.SmokeTestFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTest.LoginTestWithPageObject;
import org.postTest.CreateNewPostTest;
import org.registrationTest.ValidationMessageTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        ValidationMessageTest.class,
        CreateNewPostTest.class,
        ApiTests.class
})


public class SmokeSuite {
}
