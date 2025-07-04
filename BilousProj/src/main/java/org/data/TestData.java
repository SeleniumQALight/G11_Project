package org.data;

import org.utils.ConfigProvider;

import java.util.Locale;

public class TestData {
    public static final String VALID_LOGIN = System.getProperty
            ("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD = "123456qwerty";

    public static final String VALID_LOGIN_API = "bilous55555".toLowerCase(Locale.ROOT);
    public static final String VALID_PASSWORD_API = "bil123456789".toLowerCase(Locale.ROOT);

    public static double currencyBuyApi;
    public static double currencySaleApi;

    public static double currencyBuyUi;
    public static double currencySaleUi;


}
