package org.data;

public class TestData {
    public final static String VALID_LOGIN = "qaauto";
    public final static String VALID_PASSWORD = "123456qwerty";
    public static final String INVALID_LOGIN = "qaauto" + System.currentTimeMillis();
    public static final String INVALID_PASSWORD = "123456qwerty" + System.currentTimeMillis();
}
