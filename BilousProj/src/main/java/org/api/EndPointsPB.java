package org.api;

public interface EndPointsPB {
    public static final String BASE_URL = "https://api.privatbank.ua/p24api";
    public static final String EXCHANGE_RATES = BASE_URL + "/exchange_rates";
    public static final String HOME_PAGE_PB = "https://privatbank.ua/";

    public static final String EXCHANGE_RATES_EUR_USD =
            BASE_URL + "/pubinfo";
}
