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
  private Database mySDLDataBase;
  private Logger logger = Logger.getLogger(getClass());

  @Before
  public void setUp() throws SQLException, ClassNotFoundException {
    mySDLDataBase = MySQL_Database.getDataBase();
    logger.info("MySQL database was setup");
  }

  @After
  public void tearDownDB() throws SQLException {
    mySDLDataBase.quit();
    logger.info("MySQL database connection was closed");
  }

  @Test
  public void testDataFromDB() throws SQLException, ClassNotFoundException {
    ArrayList<Map<String, String>> dataFromSeleniumTable =
            mySDLDataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE id = 77");
    logger.info(dataFromSeleniumTable);
    logger.info(dataFromSeleniumTable.size());


    final String LOGIN = "G11_viktoriia_new";

    dataFromSeleniumTable = mySDLDataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '"
            + LOGIN + "'");
    Assert.assertEquals(0, dataFromSeleniumTable.size());

    int numberOfEffectedRows =
            mySDLDataBase.changeTable("INSERT INTO seleniumTable VALUES('717','%s','%s')",
                    LOGIN, "12345");
    Assert.assertEquals("Number of insert rows", 1, numberOfEffectedRows);

    dataFromSeleniumTable = mySDLDataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '"
            + LOGIN + "'");
    Assert.assertEquals(1, dataFromSeleniumTable.size());

    int numberOfDeletedRows = mySDLDataBase
            .changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
    Assert.assertEquals("Number of deleted rows ", 1, numberOfDeletedRows);

    logger.info("------------------------------");
    DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
    logger.info(dbUtilSeleniumTable.getPassForLogin("main_user"));
  }
}
