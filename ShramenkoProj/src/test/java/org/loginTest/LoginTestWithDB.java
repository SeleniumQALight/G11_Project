package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.DB_Util_seleniumUsers;

import java.sql.SQLException;

public class LoginTestWithDB extends BaseTest {

//ДЗ№7 Тест на валідний логін. Залогінитися юзером newqaauto,
// пароль для якого витягнути з таблиці seleniumUsers
// (створивши клас для цієї таблиці(в назву класу додайте назву таблиці),
// аналогічний DB_Util, і відповідний метод для отримання паролю)

    @Test
    public void validLoginWithDB() throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers seleniumUsers = new DB_Util_seleniumUsers();
        String login = "newqaauto";
        String pass = seleniumUsers.getPassForLogin(login);

        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(login)
                .enterTextIntoInputPassword(pass)
                .clickOnButtonSighIn()
                .checkIsRedirectToHomePage();
    }
}
