package org.dataBaseTests;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.DB_Util_seleniumUsers;
import java.sql.SQLException;

public class DataBaseTestValidLoginTest extends BaseTest {
    final String LOGIN = "newqaauto";

    @Test
    public void T0221_validLoginDataBaseTest() throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
        String pass = dbUtilSeleniumUsers.getPassForLogin(LOGIN);

        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(LOGIN)
                .enterTextIntoInputPassword(pass)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSingOutVisible()
                .checkIsButtonCreatePostVisible();

        pageProvider.getLoginPage()
                .checkIsInputUserNameRegistrationFormNotVisible()
                .checkIsInputEmailInRegistrationFormNotVisible();
    }

}
