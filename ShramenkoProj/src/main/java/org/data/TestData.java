package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public static final String VALID_LOGIN = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD = "123456qwerty";

    public static final String INVALID_LOGIN = "gaauto11";
    public static final String INVALID_PASSWORD = "003456qwerty";

}
