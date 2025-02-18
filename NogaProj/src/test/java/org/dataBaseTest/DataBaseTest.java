package org.dataBaseTest;

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
        logger.info("--- Connected to DB -------");
    }

    @After
    public void tearDownDB() throws SQLException {
        mySQLDataBase.quit();
        logger.info("--- Disconnected from DB -------");
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        ArrayList<Map<String, String>> dataFromSeleniumTable = mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());

        final String LOGIN = "G11_Noga_New";

        dataFromSeleniumTable = mySQLDataBase.selectTableAsMap(
                String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfRecords = mySQLDataBase.changeTable("INSERT INTO seleniumTable VALUES('7742','%s','%s')", LOGIN, "12345");
        Assert.assertEquals("Number of insert rows",1, numberOfRecords);

        dataFromSeleniumTable = mySQLDataBase.selectTableAsMap(
                String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        Assert.assertEquals(1, dataFromSeleniumTable.size());

        int numberOfUpdatedRecords = mySQLDataBase.changeTable(
                "DELETE FROM seleniumTable WHERE login = '%s'" , LOGIN);
        Assert.assertEquals("Number of delete rows",1, numberOfUpdatedRecords);

        logger.info("____________");

        DB_Util_seleniumTable db_util = new DB_Util_seleniumTable();

        logger.info(db_util.getPassForLogin("main_user"));
    }
}
