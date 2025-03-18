package org.api;

import org.api.dto.responseDTO.ExchangePB_DTO;
import org.api.dto.responseDTO.ExchangeRateDTO;

import java.util.ArrayList;
import java.util.List;

public class ExpectedResponse {

    public static ExchangePB_DTO expectedResponse() {
        String date = "22.03.2022";
        String bank = "PB";
        int baseCurrency = 980;
        String baseCurrencyLit = "UAH";

        List<ExchangeRateDTO> exchangeRates = new ArrayList<>();

        exchangeRates.add(new ExchangeRateDTO("UAH", "AUD", 21.2610000, 21.2610000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "AZN", 16.7770000, 16.7770000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "BYN", 11.0263000, 11.0263000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "CAD", 23.0453000, 23.0453000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "CHF", 31.8074000, 31.8074000, 32.8300000, 31.2300000));
        exchangeRates.add(new ExchangeRateDTO("UAH", "CNY", 4.6305000, 4.6305000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "CZK", 1.3544000, 1.3544000, 1.4000000, 1.2000000));
        exchangeRates.add(new ExchangeRateDTO("UAH", "DKK", 4.4592000, 4.4592000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "EUR", 33.1707000, 33.1707000, 32.7500000, 32.1500000));
        exchangeRates.add(new ExchangeRateDTO("UAH", "GBP", 39.7442000, 39.7442000, 40.4000000, 38.4000000));
        exchangeRates.add(new ExchangeRateDTO("UAH", "GEL", 9.3404000, 9.3404000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "HUF", 0.0928610, 0.0928610, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "ILS", 9.0809000, 9.0809000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "JPY", 0.2541800, 0.2541800, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "KZT", 0.0669190, 0.0669190, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "MDL", 1.6295000, 1.6295000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "NOK", 3.3069000, 3.3069000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "PLN", 7.2960000, 7.2960000, 7.1200000, 6.8200000));
        exchangeRates.add(new ExchangeRateDTO("UAH", "SEK", 3.1421000, 3.1421000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "SGD", 21.7525000, 21.7525000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "TMT", 8.1301000, 8.1301000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "TRY", 2.1154000, 2.1154000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "UAH", 1.0000000, 1.0000000, null, null));
        exchangeRates.add(new ExchangeRateDTO("UAH", "USD", 29.2549000, 29.2549000, 29.5474000, 29.2549000));
        exchangeRates.add(new ExchangeRateDTO("UAH", "UZS", 0.0026336, 0.0026336, null, null));

        return new ExchangePB_DTO(date, bank, baseCurrency, baseCurrencyLit, exchangeRates);



    }
}
