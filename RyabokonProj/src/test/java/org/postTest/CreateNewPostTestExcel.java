package org.postTest;

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
public class CreateNewPostTestExcel extends BaseTest {
    Logger logger = Logger.getLogger(getClass());

    private String postTitle;

    @Test
    @Parameters(method = "parametersForCreateNewPostWithExcel")

    public void TR007_CreateNewPostTestExcel(String postTitle, String postBody, String dropDown, String checkBox,
                                             String successMessage, String uniquePost) {
        postTitle = String.format(postTitle, "TR007_RyabokonExcelPost", Utils_Custom.getDateAndTimeFormatted());
        this.postTitle = postTitle;
        postBody = String.format(postBody, "New Post with Excel");

        pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage();

        pageProvider.getCreateNewPostPage().enterTextIntoInputTitle(postTitle)
                .enterTextIntoInputBody(postBody)
                .selectValueInDropDownAccess(dropDown)
                .setCheckboxState(checkBox)
                .clickOnSaveNewPostButton()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(successMessage);
        pageProvider.getPostPage().checkTextThisPostWasWrittenIsVisible(uniquePost);


        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(postTitle, 1);


    }
    @After
    public void deletePost() {
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(postTitle)

        ;
    }
    public Collection parametersForCreateNewPostWithExcel() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        logger.info("Unique post identier: " + Utils_Custom.getDateAndTimeFormatted());
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}