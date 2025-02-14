package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String VALID_LOGIN = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD = "123456qwerty";
    public final static String CHECK = "check";
    public final static String UNCHECK="uncheck";
    public final static String YES = "yes";
    public final static String NO ="no";
}
