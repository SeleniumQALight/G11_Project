package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.DB_Util_seleniumUsers;

import java.sql.SQLException;

public class LoginTestWithDB extends BaseTest {
  final String login = "newqaauto";

  @Test
  public void T00012_validLoginWithDB() throws SQLException, ClassNotFoundException {
    DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
    String password = dbUtilSeleniumUsers.getPasswordForUser(login);
    {
      pageProvider.getLoginPage()
              .openPage()
              .enterTextIntoInputLogin(login)
              .enterTextIntoInputPassw0rd(password)
              .clickOnButtonSignIn();

      pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible()
              .checkIsButtonCreatePostVisible();

      pageProvider.getLoginPage()
              .checkIsInputUsernameNotVisible()
              .checkIsInputPasswordNotVisible();
    }
  }
}
