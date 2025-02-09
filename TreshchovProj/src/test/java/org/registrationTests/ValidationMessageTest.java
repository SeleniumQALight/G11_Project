package org.registrationTests;

import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;

import static org.data.RegistrationValidationMessages.*;

public class ValidationMessageTest extends BaseTest {

    @Test
    public void TC023_ValidationMessagesTest(){
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField("tr")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
                .checkErrorsMessages(ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);
    }

}
