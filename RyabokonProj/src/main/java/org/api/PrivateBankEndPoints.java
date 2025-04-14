package org.api;

public interface PrivateBankEndPoints {


        String BASE_URLBank = "https://api.privatbank.ua";
        String CURRENCY_EXCHANGE = BASE_URLBank + "/p24api/exchange_rates";
        String CURRENCY_RATES_API = BASE_URLBank + "/p24api/pubinfo?json&exchange&coursid=5";

    }

