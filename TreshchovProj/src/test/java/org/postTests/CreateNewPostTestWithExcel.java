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

    String titleUnique;

    @Test
    @Parameters(method = "parametersForCreatePostTest")
    public void HW6_createNewPostUsingExcel(String title, String body, String dropdownSelect, String checkBox, String expectedMessage, String expectedUniquePost) {
        titleUnique = String.format(title, "Treshchov Test", Utils_Custom.getDateAndTimeFormatted());
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
                checkIsAlertSuccessPresent().
                checkIsThisMessageUniqueText(expectedUniquePost).
                checkTextThisPostWasWrittenIsVisible(dropdownSelect).
                checkTextInSuccessMessage(expectedMessage)
        ;

        pageProvider.getPostPage().
                getHeaderElement().
                clickOnButtonMyProfile().checkIsRedirectToMyProfilePage().checkPostWithTitlePresent(titleUnique, 1)

        ;
    }

    @After
    public void deletePosts(){
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded().getHeaderElement().clickOnButtonMyProfile().checkIsRedirectToMyProfilePage().deletePostsTillPresent(titleUnique);

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
