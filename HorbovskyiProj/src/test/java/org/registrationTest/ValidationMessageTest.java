package org.registrationTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessageTest extends BaseTest {

    @Test
    @Parameters(method = "validationMessageTestData")
    public void TC023_validationMessageTest(String username, String email, String password, String errorMessage) {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistaionUserNameField(username)
                .enterTextIntoRegistaionEmailField(email)
                .enterTextIntoRegistaionPasswordField(password)
                .checkErrorsMessages(errorMessage)
        ;
    }

    public Object[][] validationMessageTestData() {
        return new Object[][]{
                {"tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"ttrr", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},

        };

    }
}
