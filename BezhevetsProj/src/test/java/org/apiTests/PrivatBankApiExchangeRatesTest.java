package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.api.PrivatBankEndpoints.EXCHANGE_RATES;

import org.api.dto.responseDTO.ExchangeRateObjectDTO;
import org.api.dto.responseDTO.ExchangeRatesResponseDTO;
import org.assertj.core.api.SoftAssertions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

public class PrivatBankApiExchangeRatesTest {
    private final String TEST_DATE = "22.03.2022";
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeRates() {
        ExchangeRatesResponseDTO actualResponse = given()
                .contentType(ContentType.JSON)
                .queryParam("date", TEST_DATE) // Використовуємо константу
                .log().all()
                .when()
                .get(EXCHANGE_RATES)
                .then()
                .log().all()
                .statusCode(200)
                // Method #1: RestAssured asserts
                .body("date", equalTo(TEST_DATE))
                .body("bank", equalTo("PB"))
                .body("baseCurrency", equalTo(980))
                .body("baseCurrencyLit", equalTo("UAH"))
                .body("exchangeRate.baseCurrency", everyItem(equalTo("UAH")))
                // Method #2: Extract to DTO
                .extract().as(ExchangeRatesResponseDTO.class);

        logger.info("Date = " + actualResponse.getDate()); // Додаємо логування окремих полів
        logger.info("Bank = " + actualResponse.getBank());
        logger.info("Number of exchange rates = " + actualResponse.getExchangeRate().size());
        logger.info("First exchange rate: " + actualResponse.getExchangeRate().get(0));
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
        List<ExchangeRateObjectDTO> expectedExchangeRates = expectedCurrencies.stream()
                .map(currency -> new ExchangeRateObjectDTO("UAH", currency))
                .collect(Collectors.toList());
        ExchangeRatesResponseDTO expectedResponse = new ExchangeRatesResponseDTO(
                TEST_DATE, "PB", 980, "UAH", expectedExchangeRates
        );

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB",
                        "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResponse);

        softAssertions.assertAll();
    }

}
