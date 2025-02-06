package org.registrationTest;

import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;

import static org.data.RegistrationValidationMessages.SEMICOLON;

public class ValidationMessageTest extends BaseTest {

    @Test
    public void TC023_ValidationMessageTest() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().openPage()
                .enterTextIntoRegistrationUsernameField("tr")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
                .checkErrorsMessages(RegistrationValidationMessages.ERROR_USERNAME
                + SEMICOLON + RegistrationValidationMessages.ERROR_EMAIL
                + SEMICOLON + RegistrationValidationMessages.ERROR_PASSWORD)
                ;
    }
}
