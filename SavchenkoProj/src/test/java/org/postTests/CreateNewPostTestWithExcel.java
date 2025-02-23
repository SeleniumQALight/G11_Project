package org.postTests;

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
import java.util.Random;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostTestWithExcel extends BaseTest{

    Random random = new Random();
    int randomNumber = random.nextInt(1000);

    Logger logger = Logger.getLogger(getClass());
    @Test
    @Parameters(method = "parametersForPostCreation")
    public void TR001_createNewPostWithExcel(String postTitle, String postBody, String numberOfPeople,
                                             String checkboxState, String successMessage, String isPostUnique) {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForUserElement().clickOnButtonCreatePost()
                .enterTextIntoInputTitle(postTitle + randomNumber)
                .selectValueInDropDownAccess(numberOfPeople)
                .enterTextIntoInputBody(postBody)
                .selectUniquePostCheckbox(checkboxState)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostUnique(isPostUnique)
                .checkIsSuccessMessageDisplayed()
                .checkTextInThisPostWasWrittenIsVisible(numberOfPeople)
                .checkTextInSuccessMessage(successMessage)
        ;

        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitlePresent(postTitle + randomNumber, 1)
        ;

        pageProvider.getHomePage().openHomePageAndLoginIfNeed()
                .getHeaderForUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(postTitle);

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
