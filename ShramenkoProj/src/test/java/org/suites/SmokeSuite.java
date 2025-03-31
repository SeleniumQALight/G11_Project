package org.suites;

import org.apiTests.ApiTests;
import org.categories.SmokeTestsFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTest.LoginTestWithPageObject;
import org.postTests.CreateNewPostTest;
import org.registrationTests.ValidationMessageTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestsFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        ValidationMessageTest.class,
//        CreateNewPostTest.class,
        //з цього списку виконаються тільки ті, що відповідають фільтру
        //тобто ті, що помарковані як для smoke теста

        ApiTests.class
})

public class SmokeSuite {
}
