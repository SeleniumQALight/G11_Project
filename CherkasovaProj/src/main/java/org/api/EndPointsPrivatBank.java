package org.api;

public interface EndPointsPrivatBank {
    String BASE_URL = "https://api.privatbank.ua";
    String EXCHANGE_RATE = BASE_URL + "/p24api/exchange_rates";
    String CURRENCY_RATES = BASE_URL + "/p24api/pubinfo";
}
