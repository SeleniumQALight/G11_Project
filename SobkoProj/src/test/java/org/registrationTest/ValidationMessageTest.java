package org.registrationTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.data.RegistrationValidationMessages;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestsFilter.class)
public class ValidationMessageTest extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void TC023ValidationMessageTest(String usersName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationNameField(usersName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedMessages);
    }

    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][]{
                {"tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"ttrr", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}
        };
    }
    @Test
    public void TC024ValidationMessageTestUseTabAndEnter() {
        pageProvider.getLoginPage().useTabAndEnterButtonsToSignUpUser("tr", "tr", "tr");
        pageProvider.getLoginPage().checkErrorsMessages(RegistrationValidationMessages.ERROR_USERNAME
                + RegistrationValidationMessages.SEMICOLON
                + RegistrationValidationMessages.ERROR_EMAIL
                + RegistrationValidationMessages.SEMICOLON
                + RegistrationValidationMessages.ERROR_PASSWORD);

    }

}
