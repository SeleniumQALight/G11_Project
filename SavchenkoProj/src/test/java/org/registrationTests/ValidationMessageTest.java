package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.categories.SmokeTestFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;


import static org.data.RegistrationValidationMessages.*;
@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class ValidationMessageTest extends BaseTest {


    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void TC023_validationMessagesTest(String username, String email, String password, String message) {
       pageProvider.getLoginPage().openPage();
       pageProvider.getLoginPage()
               .enterTextIntoRegistrationUserNameField(username)
               .enterTextIntoRegistrationEmailField(email)
               .enterTextIntoRegistrationPasswordField(password)
            .checkErrorMessages(message);

    }

    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][]{
                {"tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"ttr", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}
        };

    }
}
