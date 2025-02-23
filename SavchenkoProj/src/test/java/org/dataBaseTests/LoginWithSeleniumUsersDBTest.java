package org.dataBaseTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.DB_Util_seleniumUsers;

import java.sql.SQLException;

public class LoginWithSeleniumUsersDBTest extends BaseTest {
    final String LOGIN = "newqaauto";

    @Test
    public void validLoginWithDBData() throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
        String password = dbUtilSeleniumUsers.getPassForLogin(LOGIN);

        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(LOGIN)
                .enterTextIntoInputPassword(password)
                .clickOnButtonSignIn()
        ;

        pageProvider.getHomePage().getHeaderForUserElement().isButtonSignOutVisible();
    }
}
