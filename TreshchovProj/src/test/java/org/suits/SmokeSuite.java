package org.suits;

import org.LoginTest.LoginTestWithPageObject;
import org.apiTests.apiTests;
import org.categories.SmokeTestsFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.postTests.CreateNewPostTest;
import org.registrationTests.ValidationMessageTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestsFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        ValidationMessageTest.class,
        CreateNewPostTest.class,
        apiTests.class
})
public class SmokeSuite {
}
