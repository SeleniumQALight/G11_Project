package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.*;

import java.io.IOException;
import java.sql.SQLException;

public class LoginTestWithDB extends BaseTest {


    @Test
    public void T0022_validLoginWithDB() throws IOException, SQLException, ClassNotFoundException {

        String loginDB = new DB_Util_seleniumUsers().getLoginFromDB();
        String passDB = new DB_Util_seleniumUsers().getPassForLogin(loginDB);

        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(loginDB)
                .enterTextIntoPassword(passDB)
                .clickInButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }
}
