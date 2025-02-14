package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.ConfigProvider;
import org.utils.ExcelSpreadsheetData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessageTestWithExcel extends BaseTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void TC0231_validationMessageTest(String username, String email, String password, String expectedMessage) {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(username)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessage(expectedMessage);

    }

    public Collection parametersForValidationMessagesTest() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "registrationErrors";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
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
