package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.utils.ConfigProvider;
import org.utils.ExcelDriver;
import org.utils.Utils_Custom;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@RunWith(Parameterized.class)
public class CreateNewPostTestWithExcel extends BaseTest {
    final String POST_TITLE = "TR003_TanyaChe_" + Utils_Custom.getDateAndTimeFormatted();
    private final int rowNumber;

        public CreateNewPostTestWithExcel(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> testData() {
            return Arrays.asList(new Object[][] {
                    {1}, {2}, {3} // Test with three different rows from Excel
            });
        }

    @Before
    public void loginAndRedirectToCreateNewPostPage()  {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                ;

    }

    @Test
    public void T0022_createNewPost() throws IOException {
        Map<String, String> dataForCreateNewPost =
                ExcelDriver.getDataRow(ConfigProvider.configProperties.DATA_FILE_PATH(), "createPostWithExcel", rowNumber);


        pageProvider.getCreateNewPostPage()
                .enterTextIntoInputTitle(dataForCreateNewPost.get(POST_TITLE))
                .selectValueInDropDownAccess(dataForCreateNewPost.get("access"))
                .enterTextIntoInputBody(dataForCreateNewPost.get("New Post Body Excel %s"))
                .setCheckboxState(dataForCreateNewPost.get("checkbox"))
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSussesMessageDisplayed()
                .checkTextThisPostWasWrittenIsVisible(dataForCreateNewPost.get("access"))
                .checkTextInSuccessMessage("New post successfully created.")
                .checkMessageUnique()
        ;

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .checkIsPostWithTitleIsPresent(dataForCreateNewPost.get(POST_TITLE), 1)
        ;
    }


}
