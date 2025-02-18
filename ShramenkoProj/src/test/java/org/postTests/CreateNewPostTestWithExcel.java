package org.postTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.ConfigProvider;
import org.utils.ExcelDriver;
import org.utils.ExcelSpreadsheetData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@RunWith(JUnitParamsRunner.class) // буде запускатись кілька разів з різними параметрами

public class CreateNewPostTestWithExcel extends BaseTest {

    Logger logger = Logger.getLogger(getClass());
    private String currentTitle; //щоб зберегти її для after

    @Test
    @Parameters(method = "parametersForCreatePost")
    public void T00058_createNewPostWithExcel(
            String title, String bodyText, String dropdown, String checkbox,
            String expectedMessage, String expectedUnique) throws IOException {

        this.currentTitle = title; // перевизначаємо її на нове значення з excel

        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE()
                        , "validLogOn");

        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin((dataForValidLogin.get("login")))
                .enterTextIntoInputPassword(dataForValidLogin.get("pass"))
                .clickOnButtonSighIn()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(title)
                .selectValueInDropDownAccess(dropdown)
                .enterTextIntoInputBody(bodyText)
                .checkboxUniqueState(checkbox)
                .clickOnButtonSaveNewPost().checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTestThisPostWasWrittenIsVisible(dropdown) //?
                .checkTextInSuccessMessage(expectedMessage)
                .checkStateUniquePost(expectedUnique);

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(title, 1) //працює поки наш пост тільки один
        ;
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(currentTitle)
        ;
    }

    public Collection parametersForCreatePost() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuitNew.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

}
