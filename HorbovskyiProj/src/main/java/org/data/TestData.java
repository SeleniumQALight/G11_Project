package org.data;

import org.utils.ConfigProvider;
import org.utils.Utils_Custom;

import java.util.Locale;

public class TestData {
    public final static String VALID_LOGIN = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD = "123456qwerty";

    public static final String INVALID_LOGIN = "qaauto" + System.currentTimeMillis();
    public static final String INVALID_PASSWORD = "123456qwerty" + System.currentTimeMillis();

    public static final String POST_TITLE = "TR003Horbovskyi" + Utils_Custom.getDateAndTimeFormatted();

    public static final String VALID_LOGIN_API = "g11Horbovskyi".toLowerCase();
    public static final String VALID_PASSWORD_API = "Horbovskyi123";


}
