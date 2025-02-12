package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)

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
        Actions actions = new Actions(webDriver);

        // Step 1: Open login page
        pageProvider.getLoginPage().openPage();

        // Step 2: Tab to the User Name field in the Registration form
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys(Keys.TAB).perform();

        // Step 3: Enter invalid value into the User Name field
        actions.sendKeys("US").perform();

        // Step 4: Tab to the Email field in the Registration form
        actions.sendKeys(Keys.TAB).perform();

        // Step 5: Enter invalid value into the Email field
        actions.sendKeys("invalidEmail").perform();

        // Step 6: Tab to the Password field in the Registration form
        actions.sendKeys(Keys.TAB).perform();

        // Step 7: Enter invalid value into the Password field
        actions.sendKeys("Password").perform();

        // Step 8: Press Enter
        actions.sendKeys(Keys.ENTER).perform();

        // Step 9: Check that three error messages are displayed
        pageProvider.getLoginPage().checkErrorsMessages(ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);

    }



}
