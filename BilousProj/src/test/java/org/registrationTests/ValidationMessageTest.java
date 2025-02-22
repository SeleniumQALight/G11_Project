package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.categories.SmokeTestFilter;
import org.data.RegistrationValidationMessages;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.pages.ParantPage;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class ValidationMessageTest extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void TC023_validationMessagesTest(
            String username, String email, String password, String expectedErrorsMessages
    ) {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(username)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedErrorsMessages);
        ;

    }

    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][]{
                {"tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"ttrr", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
        };
    }
    @Test
    public void TC024_invalidRegistration() {

        pageProvider.getLoginPage().openPage()
                .pressTabKey(5);
        pageProvider.getLoginPage().enterTextIntoField("US");
        pageProvider.getLoginPage().pressTabKey(1);
        pageProvider.getLoginPage().enterTextIntoField("invalidEmail");
        pageProvider.getLoginPage().pressTabKey(1);
        pageProvider.getLoginPage().enterTextIntoField("Password");
        pageProvider.getLoginPage().pressTabKey(1);
        pageProvider.getLoginPage().checkErrorsMessages(ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);
    }



}
