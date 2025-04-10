package org.api;

public interface PrivatBankEndPoints {
    //RestAssured бібліотека чекає на ендпоінти в інтерфейсі
    String PRIVATE_BANK_BASE_URL = "https://api.privatbank.ua";
    String GET_EXCHANGE_RATES = PRIVATE_BANK_BASE_URL + "/p24api/exchange_rates";
    String GET_EXCHANGE_INFO = PRIVATE_BANK_BASE_URL + "/p24api/pubinfo";
}
