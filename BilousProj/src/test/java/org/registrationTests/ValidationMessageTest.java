package org.registrationTests;

import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;

public class ValidationMessageTest extends BaseTest {

    @Test
    public void TC023_validationMessagesTest() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField("tr")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
                .checkErrorsMessages(RegistrationValidationMessages.ERROR_USERNAME
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_EMAIL
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_PASSWORD
                    );
        ;

    }


}
