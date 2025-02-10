package org.registrationTests;

import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;

public class ValidationMessageTest extends BaseTest {

    @Test
    public void TC023_validationMessageTest() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField("tr")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
                .checkErrorsMessage(RegistrationValidationMessages.ERROR_USERNAME
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_EMAIL
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_PASSWORD);

    }

}
