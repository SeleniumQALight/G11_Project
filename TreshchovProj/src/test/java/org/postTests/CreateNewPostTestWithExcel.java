package org.postTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.ConfigProvider;
import org.utils.ExcelSpreadsheetData;
import org.utils.Utils_Custom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostTestWithExcel extends BaseTest {
    Logger logger = Logger.getLogger(CreateNewPostTestWithExcel.class);
    //GUID =

    final String TITLE =  "Treshchov Test" + Utils_Custom.getDateAndTimeFormatted();
    @Test
    @Parameters(method = "parametersCreatePostTest")
    public void TR003_createNewPost(String title, String body, String dropdownSelect, String checkBox, String expectedMessage, String expectedUniquePost) {
        String titleUnique = String.format(title, "Treshchov Test", Utils_Custom.getDateAndTimeFormatted());
        pageProvider.getLoginPage().
                openLoginAndFillLoginFormWithValidData().
                checkIsRedirectToHomePage().
                getHeaderElement().
                clickOnButtonCreatePost().
                checkIsRedirectToCreateNewPostPage().
                enterTextIntoInputTitle(titleUnique).
                selectValueInDD(dropdownSelect).
                enterTextIntoInputBody(String.format(body,"Treshchov Test")).
                checkBoxSelection(checkBox).
                clickOnButtonSavePost().
                checkIsRedirectToPostPage().
                CheckIsAlertSuccessPresent(expectedMessage).
                checkTextThisPostWasWrittenIsVisible(dropdownSelect).
                checkTextInSuccessMessage(expectedUniquePost)
        ;

        pageProvider.getPostPage().
                getHeaderElement().
                clickOnButtonMyProfile().checkIsRedirectToMyProfilePage().checkPostWithTitlePresent(titleUnique, 1)

        ;
    }



    public Collection parametersForCreatePostTest() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
