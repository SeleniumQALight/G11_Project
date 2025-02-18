package org.registrationTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
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
    public void TC025ValidationMessageTest(String usersName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationNameField(usersName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedMessages);
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
}


