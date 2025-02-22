package org.suits;

import org.categories.SmokeTestsFilter;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.loginTest.LoginTestWithPageObject;
import org.pages.CreateNewPostPage;
import org.registrationTest.ValidationMessageTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestsFilter.class)
@Suite.SuiteClasses({LoginTestWithPageObject.class,
        ValidationMessageTest.class,
        CreateNewPostPage.class})
public class SmokeSuit {

}
