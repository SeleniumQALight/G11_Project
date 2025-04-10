package org.data;

public class TestDataPrivatBank {
    public static Double RATE_UI_BUY;
    public static Double RATE_API_BUY;

    public static Double RATE_UI_SELL;
    public static Double RATE_API_SELL;

    public static Double getRateUiBuy() {
        return RATE_UI_BUY;
    }

    public static void setRateUiBuy(Double rateUiBuy) {
        RATE_UI_BUY = rateUiBuy;
    }

    public static Double getRateApiBuy() {
        return RATE_API_BUY;
    }

    public static void setRateApiBuy(Double rateApiBuy) {
        RATE_API_BUY = rateApiBuy;
    }

    public static Double getRateUiSell() {
        return RATE_UI_SELL;
    }

    public static void setRateUiSell(Double rateUiSell) {
        RATE_UI_SELL = rateUiSell;
    }

    public static Double getRateApiSell() {
        return RATE_API_SELL;
    }

    public static void setRateApiSell(Double rateApiSell) {
        RATE_API_SELL = rateApiSell;
    }
}
