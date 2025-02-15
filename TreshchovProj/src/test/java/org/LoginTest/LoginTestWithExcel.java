package org.LoginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.ConfigProvider;
import org.utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithExcel extends BaseTest {
    @Test
    public void t0011_validLogin() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");


        pageProvider.getLoginPage().openPage().
                enterTextIntoInputLogin(dataForValidLogin.get("login")).
                enterTextIntoInputPassword(dataForValidLogin.get("pass")).
                clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();


    }

}
