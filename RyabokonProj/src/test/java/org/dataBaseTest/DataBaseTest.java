package org.dataBaseTest;

import org.junit.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.utils.DB_Util_seleniumTable;
import org.utils.Database;
import org.utils.MySQL_Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DataBaseTest {
    private Database mySQL_DataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUpDB() throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("MySQL database was setup");// some code
    }

    @After
    public void tearDownDB() throws SQLException {
        mySQL_DataBase.quit();
        logger.info("MySQL database was closed");
        // some code
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        ArrayList<Map<String, String>> dataFromSeleniumTable = mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE id = 16");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());
        // some code
        final String LOGIN = "G11_Ryabokon";
        dataFromSeleniumTable = mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfEffectedRows = mySQL_DataBase.changeTable("INSERT INTO seleniumTable VALUES('7742222','%s','%s')", LOGIN, "12345");
        Assert.assertEquals(1, numberOfEffectedRows);

        dataFromSeleniumTable = mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");

        int numberOfDeletedRows = mySQL_DataBase.changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        Assert.assertEquals("Number of Deleted Rows", 1, numberOfDeletedRows);

        logger.info("_____________________________");
        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("main_user"));
    }
}