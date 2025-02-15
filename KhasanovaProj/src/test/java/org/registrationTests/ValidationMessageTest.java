package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessageTest extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessageTest")
    public void TC023_validationMessageTest(String username, String email, String password, String expectedMessage) {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(username)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessage(expectedMessage);

    }

    public Object[][] parametersForValidationMessageTest() {
        return new Object[][]{
                {"tr", "tr", "tr", RegistrationValidationMessages.ERROR_USERNAME
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_EMAIL
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_PASSWORD},
                {"test7", "tr", "tr", RegistrationValidationMessages.ERROR_EMAIL
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_PASSWORD}
        };
        }

    @Test
    public void TC010_validationMessageWithTabAndEnterButtonsTest() {
        pageProvider.getLoginPage().openPage().pressTabButton();
        pageProvider.getLoginPage().pressTabButton();
        pageProvider.getLoginPage().pressTabButton();
        pageProvider.getLoginPage().pressTabButton();
        pageProvider.getLoginPage().pressTabButton();
        pageProvider.getLoginPage().sendKeysUsingActions("tr");
        pageProvider.getLoginPage().pressTabButton();
        pageProvider.getLoginPage().sendKeysUsingActions("tr");
        pageProvider.getLoginPage().pressTabButton();
        pageProvider.getLoginPage().sendKeysUsingActions("tr");
        pageProvider.getLoginPage().pressEnterButton();
        pageProvider.getLoginPage()
                .checkErrorsMessage(RegistrationValidationMessages.ERROR_USERNAME
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_EMAIL
                        + RegistrationValidationMessages.SEMICOLON
                        + RegistrationValidationMessages.ERROR_PASSWORD);
    }

}
