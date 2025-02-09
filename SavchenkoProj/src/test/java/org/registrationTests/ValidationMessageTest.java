package org.registrationTests;

import org.baseTest.BaseTest;
import org.junit.Test;


import static org.data.RegistrationValidationMessages.*;

public class ValidationMessageTest extends BaseTest {

    @Test
    public void TC023_validationMessagesTest() {
       pageProvider.getLoginPage().openPage();
       pageProvider.getLoginPage()
               .enterTextIntoRegistrationUserNameField("tr")
               .enterTextIntoRegistrationEmailField("tr")
               .enterTextIntoRegistrationPasswordField("tr")
        .checkErrorMessages(ERROR_USERNAME
                + SEMICOLON
                + ERROR_EMAIL
                + SEMICOLON
                + ERROR_PASSWORD
                );

    }
}
