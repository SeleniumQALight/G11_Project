package org.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTest.LoginTestWithPageObject;
import org.pages.CreateNewPostPage;
import org.registrationTests.ValidationMessageTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostPage.class,
        ValidationMessageTest.class
})

public class RegressionSuite {
}
