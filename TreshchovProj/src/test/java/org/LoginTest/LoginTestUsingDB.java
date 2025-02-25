package org.LoginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.DB_Util_seleniumUsers;

import java.sql.SQLException;

public class LoginTestUsingDB extends BaseTest {
    final String LOGIN = "newqaauto";

    @Test
    public void HW7_validLoginUsingDB() throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers db_util_seleniumUsers = new DB_Util_seleniumUsers();;
        pageProvider.getLoginPage().openPage().
                enterTextIntoInputLogin(LOGIN).
                enterTextIntoInputPassword(db_util_seleniumUsers.getPassForLogin(LOGIN)).
                clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();


    }
}
