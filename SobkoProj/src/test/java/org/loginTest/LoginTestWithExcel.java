package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.ConfigProvider;
import org.utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;


public class LoginTestWithExcel extends BaseTest {


    @Test
    public void T0001_validLogin() throws IOException {
        Map<String, String> dataForValidationLogin=
            ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(dataForValidationLogin.get("login"))
                .enterTextIntoInputPassword(dataForValidationLogin.get("pass"))
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkIsInputLoginNotVisible();
        pageProvider.getLoginPage().checkIsInputPasswordNotVisible();
    }
}
