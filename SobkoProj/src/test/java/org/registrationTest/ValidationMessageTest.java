package org.registrationTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.data.RegistrationValidationMessages;

public class ValidationMessageTest extends BaseTest {

    @Test
    public void TC023ValidationMessageTest() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationNameField("tr")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
                .checkErrorsMessages(RegistrationValidationMessages.ERROR_USERNAME
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_EMAIL
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_PASSWORD);
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
