package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.PrivatBankEndpoints;
import org.api.dto.responseDTO.PrivatBankCurrenciesDTO;
import org.api.dto.responseDTO.PrivatBankExchangeRatesDTO;
import org.assertj.core.api.SoftAssertions;
import org.data.PrivatBankCurrencies;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PrivatBankApiTests {

    private Logger logger = Logger.getLogger(getClass());


    @Test
    public void getExchangeRates() {
        PrivatBankExchangeRatesDTO actualResponse =
                given().queryParam("date", "22.03.2022")
                        .contentType(ContentType.JSON)
                        .when()
                        .get(PrivatBankEndpoints.EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().body().as(PrivatBankExchangeRatesDTO.class);

        logger.info("Date = " + actualResponse.getDate());
        logger.info("Bank = " + actualResponse.getBank());
        logger.info("BaseCurrency = " + actualResponse.getBaseCurrency());
        logger.info("BaseCurrencyLit = " + actualResponse.getBaseCurrencyLit());

        PrivatBankCurrenciesDTO[] exchangeRate = new PrivatBankCurrenciesDTO[PrivatBankCurrencies.CURRENCIES.length];

        for (int i = 0; i < PrivatBankCurrencies.CURRENCIES.length; i++) {
            exchangeRate[i] = new PrivatBankCurrenciesDTO("UAH", PrivatBankCurrencies.CURRENCIES[i]);
        }

        PrivatBankExchangeRatesDTO expectedResponse =
                new PrivatBankExchangeRatesDTO(
                        "22.03.2022",
                        "PB",
                        980,
                        "UAH",
                        exchangeRate
                );

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResponse).usingRecursiveComparison().ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate").isEqualTo(expectedResponse);
        softAssertions.assertAll();
    }
}
