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

    final String TITLE = "Treshchov Test" + Utils_Custom.getDateAndTimeFormatted();
    @Test
    @Parameters(method = "parametersCreatePostTest")
    public void TR003_createNewPost(String title, String body, String dropdownSelect, String checkBox, String expectedMessage, String expectedUniquePost) {
        pageProvider.getLoginPage().
                openLoginAndFillLoginFormWithValidData().
                checkIsRedirectToHomePage().
                getHeaderElement().
                clickOnButtonCreatePost().
                checkIsRedirectToCreateNewPostPage().
                enterTextIntoInputTitle(TITLE).
                selectValueInDD("One Person").
                enterTextIntoInputBody("test body").
                clickOnButtonSavePost().
                checkIsRedirectToPostPage().
                CheckIsAlertSuccessPresent().
                checkTextThisPostWasWrittenIsVisible("One Person").
                checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage().
                getHeaderElement().
                clickOnButtonMyProfile().checkIsRedirectToMyProfilePage().checkPostWithTitlePresent(TITLE, 1)

        ;
    }

    @After
    public void deletePosts(){
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded().getHeaderElement().clickOnButtonMyProfile().checkIsRedirectToMyProfilePage().deletePostsTillPresent(TITLE);

    }

    public Collection parametersForCreatePostTest() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "registrationErrors";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
