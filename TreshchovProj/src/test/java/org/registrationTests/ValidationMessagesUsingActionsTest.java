package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesUsingActionsTest extends BaseTest {

    @Test
    @Parameters(method = "parametersForHW5_T0004_ValidationMessagesUsingActionsTest")
    public void HW5_T0004_ValidationMessagesUsingActionsTest(String username, String email, String password, String expectedErrors){
    pageProvider.getLoginPage().
            openPageAndMoveToRegistrationFieldUserNameUsingActions().enterValueUsingActions(username).
            switchToNextFieldUsingActions().enterValueUsingActions(email).
            switchToNextFieldUsingActions().enterValueUsingActions(password).
            switchToNextFieldUsingActions().checkErrorsMessages(expectedErrors);
    }

    public Object[][] parametersForHW5_T0004_ValidationMessagesUsingActionsTest() {
        return new Object[][]{
                { "tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD },
                { "ttrr", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD },

        };
    }


}
