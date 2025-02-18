package org.postTest;

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
import org.junit.After;


@RunWith(JUnitParamsRunner.class)
public class CreateNewPostTestWithExcel extends BaseTest {

    Logger logger = Logger.getLogger(getClass());
    @Test
    @Parameters(method = "parametersForPostCreation")
    public void TR001_createNewPostWithExcel(String postTitle, String postBody, String numberOfPeople,
                                             String checkboxState, String successMessage, String isPostUnique) {
        pageProvider.getLoginPage()
                .openLoginPageAndLoginFormWithValidCreds()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .enterTextIntoInputTitle(postTitle)
                .selectValueInDropDownAccess(numberOfPeople)
                .enterTextIntoInputBody(postBody)
                .setUniquePostCheckboxState(checkboxState)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsPostUniqueCorrectState(isPostUnique)
                .checkIsSuccessMessageDisplayed()
                .checkTextThisPostWasWrittenAndVisible(numberOfPeople)
                .checkTextInSuccessMessage(successMessage)
        ;

        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(postTitle, 1)
        ;

        pageProvider.getHomePage().openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(postTitle);

    }
    public Collection parametersForPostCreation() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createNewPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

}
