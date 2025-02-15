package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessageTest extends BaseTest {
    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void T0023_ValidationMessageTest(
            String userName, String email, String password, String expectedMessages
    ) {
        pageProvider.getLoginPage()
                .openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorMessages(expectedMessages);
    }

    public Object[][] parametersForValidationMessagesTest(){
        return new Object[][]{
                {"tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"ttrr", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}

        };
    }
}
