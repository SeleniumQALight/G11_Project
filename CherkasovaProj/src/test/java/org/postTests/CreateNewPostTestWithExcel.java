
package org.postTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.ConfigProvider;
import org.utils.ExcelSpreadsheetData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;


@RunWith(JUnitParamsRunner.class)
public class CreateNewPostTestWithExcel extends BaseTest {

    Logger logger = Logger.getLogger(getClass());
    private String actualTitle;

    @Before
    public void loginAndRedirectToCreateNewPostPage() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
        ;

    }

    @Test
    @Parameters(method = "parametersForCreatePost")
    public void T0022_createNewPostWithExcel(
            String title, String body, String dropdown, String checkbox,
            String expectedMessage, String postUnique) {
        title = String.format(title, "Tanya");
        this.actualTitle = title;
        body = String.format(body, "TanyaChe");


        pageProvider.getCreateNewPostPage()
                .enterTextIntoInputTitle(title)
                .selectValueInDropDownAccess(dropdown)
                .enterTextIntoInputBody(body)
                .setCheckboxState(checkbox)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSussesMessageDisplayed()
                .checkTextThisPostWasWrittenIsVisible(dropdown)
                .checkTextInSuccessMessage(expectedMessage)
                .checkPostUnique(postUnique);

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .checkIsPostWithTitleIsPresent(title, 1)
        ;
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(actualTitle)
        ;
    }

    public Collection parametersForCreatePost() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

}





