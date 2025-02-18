package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class) // буде запускатись кілька разів з різними параметрами

public class ValidationMessageTestUsingTabAndEnter extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessageTest")
    public void T00056_validationMessageTestUsingTabEnterButtons(
            String userName, String email, String password, String expectedMessage) {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoLoginWithTab(userName)
                .enterTextIntoEmailWithTab(email)
                .enterTextIntoPasswordWithTabAndEnter(password)
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
