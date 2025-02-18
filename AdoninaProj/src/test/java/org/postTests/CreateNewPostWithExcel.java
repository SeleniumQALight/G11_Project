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
public class CreateNewPostWithExcel extends BaseTest {
  Logger logger = Logger.getLogger(getClass());
  private String POST_TITLE;

  @Test
  @Parameters(method = "parametersForCreateNewPostTest")
  public void TC011_createNewPostWithExcel(String title, String body, String dropdownValue, String checkboxValue,
                                           String successfulMessage, String checkPostUnique) {
    POST_TITLE = String.format(title, "TR0011_adonVA__", Utils_Custom.getDateAndTimeFormatted());
//    POST_TITLE = "TR0011_adonVA_" + Utils_Custom.getDateAndTimeFormatted();


    pageProvider.getLoginPage()
            .openLoginPageAndFillLoginFormWithValidCred()
            .checkIsRedirectOnHomePage()
            .getHeaderElement().clickOnButtonCreatePost()
            .checkIsRedirectToCreateNewPostPage()
            .enterTextIntoInputTitle(POST_TITLE)
            .selectValueDDCategory(dropdownValue)
            .enterTextIntoInputBody(body)
            .setOnCheckBoxIsPrivatePost(checkboxValue)
            .clickOnButtonSaveNewPost()
            .checkIsRedirectToPostPage()
            .checkIsSuccessMessageDisplayed()
            .checkTextThisPostWasWrittenIsVisible(dropdownValue)
            .checkTextInSuccessMessage(successfulMessage)
            .checkIsPostUniqueCheckboxChecked(checkPostUnique)
            .getHeaderElement().clickOnButtonMyProfile()
    ;
    pageProvider.getPostPage().getHeaderElement().clickOnButtonMyProfile()
            .checkIsRedirectToMyProfilePage()
            .checkPostWithTitleIsPresent(POST_TITLE, 1);
  }

  @After
  public void deletePosts() {
    pageProvider.getHomePage()
            .openHomePageAndLoginIfNeeded()
            .getHeaderElement().clickOnButtonMyProfile()
            .checkIsRedirectToMyProfilePage()
            .deletePostsTitlePresent(POST_TITLE)
    ;
  }

  public Collection parametersForCreateNewPostTest() throws IOException {
    String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH(); // + "testDataSuit.xls";
    String sheetName = "createPostWithExcel";
    boolean skipFirstRow = false;
    logger.info("Data file path: " + pathToDataFile);
    logger.info(" sheetName: " + sheetName);
    logger.info(" skipFirstRow: " + skipFirstRow);
    return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
  }

}
