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
    Logger logger = Logger.getLogger(getClass());

    private String titleOfPost;

    @Test
    @Parameters(method = "parametersForCreateNewPostTestWithExcel")

    public void TR009_createNewPostTestWithExcel(
            String postTitle, String postBody, String dropDown, String checkBox, String successMessage, String uniquePost) {
        postTitle = String.format(postTitle, "TR009_Mariana", Utils_Custom.getDateAndTimeFormatted());
        this.titleOfPost = postTitle;
        postBody = String.format(postBody, "New Post with Excel");

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage();

        pageProvider.getCreateNewPostPage()
                .enterTextIntoInputTitle(postTitle)
                .enterTextIntoInputBody(postBody)
                .selectValueInDropDownAccess(dropDown)
                .clickOnCheckBoxIfNeed(checkBox)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .cheskIsSuccessMessageDisplayed()
                .checkTextThisPostWasWrittenIsVisible(dropDown)
                .checkTextInSuccessMessage(successMessage)
                .checkTextIsThisPostUnique(uniquePost)
        ;

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(postTitle, 1);
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeed()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(titleOfPost)
        ;

    }
    public Collection parametersForCreateNewPostTestWithExcel() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }


}
