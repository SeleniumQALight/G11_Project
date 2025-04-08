package org.apiTests;

import com.beust.ah.A;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.EndPointsPrivateBank;
import org.api.dto.response.ExchangePrivateBankDTO;
import org.api.dto.response.ExchangeRatedDTO;
import org.assertj.core.api.SoftAssertions;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Test
    public void getExchangeRatesTestWithBuilder() {
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
                        .extract().body().as(ExchangePrivateBankDTO.class);
        logger.info(actualResponse);

        Set<String> expectedCurrencies = Set.of(
                "AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP",
                "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD",
                "TMT", "TRY", "UAH", "USD", "UZS"
        );

        List<ExchangeRatedDTO> expectedRates = new ArrayList<>();
        for (String currency : expectedCurrencies) {
            expectedRates.add(
                    ExchangeRatedDTO.builder()
                            .currency(currency)
                            .baseCurrency("UAH")
                            .build()
            );
        }

        ExchangePrivateBankDTO expectedResponse = ExchangePrivateBankDTO.builder()
                .date(date)
                .bank("PB")
                .baseCurrency(980)
                .baseCurrencyLit("UAH")
                .exchangeRate(expectedRates)
                .build();

        softAssertions.assertThat(actualResponse.getDate()).isEqualTo(expectedResponse.getDate());
        softAssertions.assertThat(actualResponse.getBank()).isEqualTo(expectedResponse.getBank());
        softAssertions.assertThat(actualResponse.getBaseCurrency()).isEqualTo(expectedResponse.getBaseCurrency());
        softAssertions.assertThat(actualResponse.getBaseCurrencyLit()).isEqualTo(expectedResponse.getBaseCurrencyLit());

        List<ExchangeRatedDTO> actualRates = actualResponse.getExchangeRate();

        for (ExchangeRatedDTO expected : expectedRates) {
            boolean foundMatch = actualRates.stream()
                    .anyMatch(actual ->
                            Objects.equals(actual.getCurrency(), expected.getCurrency()) &&
                                    Objects.equals(actual.getBaseCurrency(), expected.getBaseCurrency())
                    );
            softAssertions.assertThat(foundMatch)
                    .as("Expected currency %s with baseCurrency %s", expected.getCurrency(), expected.getBaseCurrency())
                    .isTrue();
        }

        softAssertions.assertAll();
    }

}






