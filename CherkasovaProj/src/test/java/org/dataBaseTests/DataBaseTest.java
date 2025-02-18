package org.dataBaseTests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utils.DB_Util_seleniumTable;
import org.utils.Database;
import org.utils.MySQL_Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DataBaseTest {

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

        final String LOGIN = "G11_TanyaChe_new";

// SELECT
        dataFromSeleniumTable = mySQLDataBase
                .selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals(0, dataFromSeleniumTable.size());

// INSERT
        int numberOfREffectedRows = mySQLDataBase
                .changeTable("INSERT INTO seleniumTable VALUES('969','%s','%s')", LOGIN, "12345");
        Assert.assertEquals("Number of insert rows", 1, numberOfREffectedRows);

        dataFromSeleniumTable = mySQLDataBase
                .selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals(1, dataFromSeleniumTable.size());

// DELETE
        int numberOfDeletedRows = mySQLDataBase
                .changeTable("DELETE FROM seleniumTable WHERE login = '%s'" , LOGIN);
        Assert.assertEquals("Number of deleted rows", 1, numberOfDeletedRows);



        logger.info("--------*--------*---------*--------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("main_user"));




    }
}
