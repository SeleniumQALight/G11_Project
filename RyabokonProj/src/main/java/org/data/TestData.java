package org.data;

import org.utils.ConfigProvider;

import java.util.Locale;

public class TestData {
    public final static String VALID_LOGIN = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD = "123456qwerty";


    public static final String VALID_LOGIN_API = "ryabokon".toLowerCase();
    public static final String VALID_PASSWORD_API = "ryabokonnata";

}
