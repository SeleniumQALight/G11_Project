package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.EndPointsPrivatBank;
import org.api.dto.responseDTO.ExchangeRateDTO;
import org.api.dto.responseDTO.PBExchangeRatesDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PrivatBankExchangeRateTest {
    final String DATE = "22.03.2022";
    private Logger logger = Logger.getLogger(getClass());

        @Test
        public void validateExchangeRate() {
            PBExchangeRatesDTO actualResponse =
            given()
                    .contentType(ContentType.JSON)
                    .queryParam("date", DATE)
                    .log().all()
                 .when()
                    .get(EndPointsPrivatBank.EXCHANGE_RATE)
                 .then()
                    .log().all()
                    .statusCode(200)
            //method #1 RestAssured assert
                    .body("date", equalTo(DATE))
                    .body("bank", equalTo("PB"))
                    .body("baseCurrency", equalTo(980))
                    .body("baseCurrencyLit", equalTo("UAH"))
                    .body("exchangeRate.baseCurrency", everyItem(equalTo("UAH")))

            //method #2 DTO
                    .extract().body().as(PBExchangeRatesDTO.class);

            logger.info("Date = " + actualResponse.getDate());
            logger.info("Bank = " + actualResponse.getBank());
            logger.info("BaseCurrency = " + actualResponse.getBaseCurrency());
            logger.info("BaseCurrencyLit = " + actualResponse.getBaseCurrencyLit());
            logger.info("Number of exchange rates = " + actualResponse.getExchangeRate().size());

            logger.info("Actual response: " + actualResponse);

            for (int i = 0; i < actualResponse.getExchangeRate().size(); i++) {
                Assert.assertEquals("BaseCurrency is not expected in exchange rate " + i,
                        "UAH", actualResponse.getExchangeRate().get(i).getBaseCurrency());

            }

            List<String> expectedCurrencies = Arrays.asList(
                    "AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP",
                    "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD",
                    "TMT", "TRY", "UAH", "USD", "UZS"
            );

            List<ExchangeRateDTO> expectedExchangeRates = expectedCurrencies.stream()
                    .map(currency -> new ExchangeRateDTO("UAH", currency))
                    .collect(Collectors.toList());

            PBExchangeRatesDTO expectedResponse = new PBExchangeRatesDTO
                    (DATE, "PB", 980, "UAH", expectedExchangeRates);


            SoftAssertions softAssertions = new SoftAssertions();

            softAssertions.assertThat(actualResponse)
                    .usingRecursiveComparison()
                    .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB",
                            "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                    .isEqualTo(expectedResponse);

            softAssertions.assertAll();
        }
    }

