package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.PrivateBankEndPoints;
import org.api.dto.responseDTO.ExchangeRateDTO;
import org.api.dto.responseDTO.PrivatBankResponseDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiTestPrivatBank {
    private Logger logger = Logger.getLogger(getClass());
    @Test
    public void testPrivatBankApi() {

        String date = "22.03.2022";
PrivatBankResponseDTO actualResponse =
        given()
                .contentType(ContentType.JSON)
                .queryParam("date", date)
                .log().all()
                .when()
                .get(PrivateBankEndPoints.CURRENCY_EXCHANGE)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(PrivatBankResponseDTO.class);


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResponse.getDate()).isEqualTo(date);
        softAssertions.assertThat(actualResponse.getBank()).isEqualTo("PB");
        softAssertions.assertThat(actualResponse.getBaseCurrency()).isEqualTo(980);
        softAssertions.assertThat(actualResponse.getBaseCurrencyLit()).isEqualTo("UAH");

        logger.info("Date: " + actualResponse.getDate());
        logger.info("Bank: " + actualResponse.getBank());
        logger.info("Base Currency: " + actualResponse.getBaseCurrency());
        logger.info("Base Currency Lit: " + actualResponse.getBaseCurrencyLit());



        for (int i = 0; i < actualResponse.getExchangeRate().size(); i++) {
            Assert.assertEquals("Base currency is not expected in exchange rate " + i,
                    "UAH", actualResponse.getExchangeRate().get(i).getBaseCurrency());
        }

        List<String> expectedCurrencies = Arrays.asList(
                "AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP",
                "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD",
                "TMT", "TRY", "UAH", "USD", "UZS"
        );

        List<ExchangeRateDTO> expectedExchangeRates = expectedCurrencies.stream()
                .map(currency -> {
                    ExchangeRateDTO rate = new ExchangeRateDTO();
                    rate.setBaseCurrency("UAH");
                    rate.setCurrency(currency);
                    return rate;
                })
                .collect(Collectors.toList());

        PrivatBankResponseDTO expectedResponse = new PrivatBankResponseDTO();
        expectedResponse.setDate(date);
        expectedResponse.setBank("PB");
        expectedResponse.setBaseCurrency(980);
        expectedResponse.setBaseCurrencyLit("UAH");
        expectedResponse.setExchangeRate(expectedExchangeRates);

        softAssertions.assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(actualResponse);

        softAssertions.assertAll();


    }

}
