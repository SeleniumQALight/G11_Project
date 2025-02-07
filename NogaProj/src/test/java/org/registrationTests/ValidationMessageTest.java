package org.registrationTests;

import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;

public class ValidationMessageTest extends BaseTest {
    @Test
    public void T0023_ValidationMessageTest() {
        pageProvider.getLoginPage()
                .openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField("tr")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
                .checkErrorMessages(RegistrationValidationMessages.ERROR_USERNAME
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_EMAIL
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_PASSWORD
                );


    }

}
