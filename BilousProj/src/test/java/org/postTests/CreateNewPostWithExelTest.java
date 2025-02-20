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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;


@RunWith(JUnitParamsRunner.class)

public class CreateNewPostWithExelTest extends BaseTest {
    private static String POST_TITLE;
    private static String POST_BODY;

    Logger logger = Logger.getLogger(getClass());

    @Test
    @Parameters(method = "parametersForCreteNewPost")

    public void TR015_createNewPostWithExel(
            String titleOfPost, String textOfBody, String dropDown, String checkBox, String successMessage, String resCheckBox) {
        POST_TITLE = String.format(titleOfPost, "Bilous", "TR015");
        POST_BODY = String.format(textOfBody, "TR015");


        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .selectValueInDropdownAccess(dropDown)
                .setOnCheckBoxIsPrivatePost(checkBox)
                .clickOnButtonSavePost()
                .checkIsRedirectOnPostPage()
                .checkTextFromCheckBoxIsVisible(resCheckBox)
                .checkIsSuccessMessageDisplayed()
                .checkTextThisPostWasWrittenIsVisible(dropDown)
                .checkTextInSuccessMessage(successMessage)

        ;
        pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectOnMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;
    }
    public Collection parametersForCreteNewPost() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();

    }

    @After
    public void deletePost() {

        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectOnMyProfilePage()
                .deletePostTillPresent(POST_TITLE)
        ;

    }



}
