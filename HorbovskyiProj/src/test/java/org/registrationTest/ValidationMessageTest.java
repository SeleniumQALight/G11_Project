package org.registrationTest;

import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;

import static org.data.RegistrationValidationMessages.*;

public class ValidationMessageTest extends BaseTest {

    @Test
    public void TC023_validationMessageTest() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistaionUserNameField("tr")
                .enterTextIntoRegistaionEmailField("tr")
                .enterTextIntoRegistaionPasswordField("tr")
                .checkErrorsMessages(ERROR_USERNAME
                        + SEMICOLON
                        + ERROR_EMAIL
                        + SEMICOLON
                        + ERROR_PASSWORD)
        ;
    }


}
