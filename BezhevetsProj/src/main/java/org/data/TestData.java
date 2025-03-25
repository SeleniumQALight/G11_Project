package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public static final String VALID_LOGIN = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD = "123456qwerty";


    public static final String VALID_LOGIN_API = "g11bezhevets".toLowerCase();
    public static final String VALID_PASSWORD_API = "123456Qwerty";
}
