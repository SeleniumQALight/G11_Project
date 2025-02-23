package org.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTest.LoginTestWithPageObject;
import org.postTests.CreateNewPostTest;
import org.registrationTests.ValidationMessageTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({  //перераховуємо всі класи, які хочемо виконати в цьому suite
     LoginTestWithPageObject.class,
     CreateNewPostTest.class,
     ValidationMessageTest.class
})

public class RegressionSuite {
}
