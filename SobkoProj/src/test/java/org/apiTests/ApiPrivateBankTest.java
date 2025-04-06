package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.EndPointsPrivateBank;
import org.api.dto.response.ExchangePrivateBankDTO;
import org.api.dto.response.ExchangeRatedDTO;
import org.assertj.core.api.SoftAssertions;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.api.EndPointsPrivateBank.BASE_PRIVATE_URL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiPrivateBankTest {
    private Logger logger = Logger.getLogger(getClass());

    SoftAssertions softAssertions = new SoftAssertions();


    @Test
    public void getExchangeRatesTest() {
        String date = "22.03.2022";
        ExchangePrivateBankDTO actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .queryParams("date", date)
                        .log().all()
                        .when()
                        .get(EndPointsPrivateBank.EXCHANGE_RATE_CURRENCY)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("bank", equalTo("PB"))
                        .body("date", equalTo("22.03.2022"))
                        .body("baseCurrency", equalTo(980))
                        .body("baseCurrencyLit", equalTo("UAH"))
                        .extract().body().as(ExchangePrivateBankDTO.class);
        logger.info(actualResponse);
        logger.info(actualResponse.getExchangeRate().get(0).getCurrency());
        
        Set<String> expectedCurrencies = Set.of(
                "AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP",
                "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD",
                "TMT", "TRY", "UAH", "USD", "UZS"
        );
        List<ExchangeRatedDTO> exchangeRates = actualResponse.getExchangeRate();
        for (ExchangeRatedDTO rate : exchangeRates) {
            softAssertions.assertThat(rate.getBaseCurrency())
                    .as("Validate baseCurrency for %s", rate.getCurrency())
                    .isEqualTo("UAH");

            softAssertions.assertThat(expectedCurrencies)
                    .as("Validate currency present %s", rate.getCurrency())
                    .contains(rate.getCurrency());
        }
        softAssertions.assertAll();
    }

}


