package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.ConfigProvider;
import org.utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithExcel extends BaseTest {

    @Test
    public void T00011_validLogin() throws IOException {
        Map<String, String> dataValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");

        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(dataValidLogin.get("login"))
                .enterTextIntoInputPassword(dataValidLogin.get("pass"))
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutVisible()
                .checkIsButtonCreatePostVisible();
    }
}
