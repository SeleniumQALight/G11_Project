package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestsFilter.class)
public class ValidationMessageTest extends BaseTest {

    @Test
    @Parameters(method = "parametersForTC023_ValidationMessagesTest")
    public void TC023_ValidationMessagesTest(String username, String email, String password, String expectedErrors) {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(username)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedErrors);
    }

    public Object[][] parametersForTC023_ValidationMessagesTest() {
        return new Object[][]{
                { "tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD },
                { "ttrr", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD },

        };
    }

}
