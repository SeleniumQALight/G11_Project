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
import java.util.Random;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostWithExcelTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    private String postTitle;

    @Test
    @Parameters(method = "parametersForCreateNewPostTest")
    public void TC010_createNewPostWithExcel(String title, String body, String dropdownValue, String checkboxValue,
                                             String successfulMessage, String checkPostUnique) {
        postTitle = String.format(title, "TC010_khasanova_", Utils_Custom.getDateAndTimeFormatted());

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForUserElement()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(postTitle)
                .selectValueInDropdownAccess(dropdownValue)
                .enterTextIntoInputBody(String.format(body, " with random number: " + new Random().nextInt(1000)))
                .setNeededStateToPostUniqueCheckBox(checkboxValue)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextThisPostWasWrittenIsVisible(dropdownValue)
                .checkTextInSuccessMessage(successfulMessage)
                .checkIsPostUniqueCheckboxChecked(checkPostUnique)
        ;

        pageProvider.getPostPage().getHeaderForUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(postTitle, 1);
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded()
                .getHeaderForUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(postTitle);
    }

    public Collection parametersForCreateNewPostTest() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

}
