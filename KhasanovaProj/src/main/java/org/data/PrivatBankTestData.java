package org.data;

public class PrivatBankTestData {
    public static String BUY_RATE_API;
    public static String SELL_RATE_API;

    public static String BUY_RATE_UI;
    public static String SELL_RATE_UI;

    public static void setRatesApi(String buy, String sale) {
        BUY_RATE_API = buy;
        SELL_RATE_API = sale;
    }

}
