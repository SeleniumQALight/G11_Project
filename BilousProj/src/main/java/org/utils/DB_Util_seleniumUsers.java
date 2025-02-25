package org.utils;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DB_Util_seleniumUsers {
    private Database mySQL_DataBase;
    Logger logger = Logger.getLogger(getClass());


    public String getPassForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String pass = mySQL_DataBase.selectValue(
                String.format("select password from seleniumUsers where login = '%s'", login)
        );
        mySQL_DataBase.quit();
        logger.info("--- Disconnected from DB -------");
        return pass;
    }

    public String getLoginFromDB() throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");
        ArrayList<Map<String, String>> dataFromSeleniumUserTable =
                mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumUsers where id = 1");
        String loginDB = dataFromSeleniumUserTable.get(0).get("login").trim();
        logger.info("Login from DB is " + loginDB);
        mySQL_DataBase.quit();
        logger.info("--- Disconnected from DB -------");
        return loginDB;
    }


}
