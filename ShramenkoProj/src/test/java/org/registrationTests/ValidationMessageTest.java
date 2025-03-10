package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class) // буде запускатись кілька разів з різними параметрами
@Category(SmokeTestsFilter.class)

public class ValidationMessageTest extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessageTest")
    public void TC023_validationMessageTest(
            String userName, String email, String password, String expectedMessage) {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedMessage)
        ;
    }

    public Object[][] parametersForValidationMessageTest() {
        return new Object[][]{
                {"tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"ttrr", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}

        };
    }


}
