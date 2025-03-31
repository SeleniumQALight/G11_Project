package org.data;

public class PrivatBankTestData {
    public static String buyRateApi;
    public static String sellRateApi;

    public static String buyRateUi;
    public static String sellRateUi;

    public static void setRatesApi(String buy, String sell) {
        buyRateApi = buy;
        sellRateApi = sell;
    }
}
