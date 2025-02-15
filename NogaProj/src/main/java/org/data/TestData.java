package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String VALID_LOGIN = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());

    public final static String VALID_PASSWORD = "123456qwerty";
}
