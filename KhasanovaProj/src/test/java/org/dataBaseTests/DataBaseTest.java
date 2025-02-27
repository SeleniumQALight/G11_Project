package org.dataBaseTests;

import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utils.DB_Util_seleniumTable;
import org.utils.DB_Util_seleniumUsers;
import org.utils.Database;
import org.utils.MySQL_Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class DataBaseTest extends BaseTest {
    private Database mySQLDataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUpDB() throws SQLException, ClassNotFoundException {
        mySQLDataBase = MySQL_Database.getDataBase();
        logger.info("MySQL database was setup");
    }

    @After
    public void tearDownDB() throws SQLException {
        mySQLDataBase.quit();
        logger.info("MySQL database connection was closed");

    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE id = 16");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());

        final String LOGIN = "VA_G1111_ne8";

        dataFromSeleniumTable = mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login='"
                + LOGIN + "'");
        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfEffectedRows =
                mySQLDataBase.changeTable("INSERT INTO seleniumTable VALUES('088898','%s','%s')", LOGIN, "088898");
        Assert.assertEquals("Number of inserted rows ", 1, numberOfEffectedRows);

        dataFromSeleniumTable = mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login='"
                + LOGIN + "'");
        Assert.assertEquals(1, dataFromSeleniumTable.size());

        int numberOfDeletedRows = mySQLDataBase
                .changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        Assert.assertEquals("Number of deleted rows", 1, numberOfDeletedRows);

        logger.info("------------------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();

        logger.info(dbUtilSeleniumTable.getPassForLogin("main_user"));

    }

    @Test
    public void validLoginWithPassFromDataBase() throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
        final String LOGIN = "newqaauto";

        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(LOGIN)
                .enterTextIntoInputPassword(dbUtilSeleniumUsers.getPassForUsersLogin(LOGIN))
                .clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonSignOutVisible();

        pageProvider.getHomePage().getHeaderForUserElement()
                .checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage()
                .checkIsUsernameInputInvisible()
                .checkIsInputPasswordInvisible()
        ;

    }


}

