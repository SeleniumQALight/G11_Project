package org.suites;

import org.api.ApiHelper;
import org.apiTests.ApiTests;
import org.categories.SmokeTestsFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTest.LoginTestWithPageObject;
import org.postTests.CreateNewPostTest;
import org.registrationTest.ValidationMessageTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestsFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
//        ValidationMessageTest.class,
//        CreateNewPostTest.class,
        ApiTests.class
})
public class SmokeSuite {
}
