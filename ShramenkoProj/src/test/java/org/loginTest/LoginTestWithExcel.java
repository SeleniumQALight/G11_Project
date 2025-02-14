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
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE()
                        , "validLogOn");

        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin((dataForValidLogin.get("login")))
                .enterTextIntoInputPassword(dataForValidLogin.get("pass"))
                .clickOnButtonSighIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();

    }
}
