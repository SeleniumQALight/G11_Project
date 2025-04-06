package org.apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.api.EndPointsPrivatBank;
import org.api.dto.responseDTO.ExchangeRatesPrivatBankDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;

public class ExchangeRatesPrivatBankTests {
    final String DATE = "22.03.2022";

    @Test
    public void verifyExchangeRatesPrivatBank() {
        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("date", DATE)
                .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATES)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        SoftAssertions softAssertions = new SoftAssertions();

        String actualDate = response.jsonPath().getString("date");
        String actualBank = response.jsonPath().getString("bank");
        int actualBaseCurrency = response.jsonPath().getInt("baseCurrency");
        String actualBaseCurrencyLit = response.jsonPath().getString("baseCurrencyLit");

        softAssertions.assertThat(actualDate).as("Field: date").isEqualTo(DATE);
        softAssertions.assertThat(actualBank).as("Field: bank").isEqualTo("PB");
        softAssertions.assertThat(actualBaseCurrency).as("Field: baseCurrency").isEqualTo(980);
        softAssertions.assertThat(actualBaseCurrencyLit).as("Field: baseCurrencyLit").isEqualTo("UAH");

        List<ExchangeRatesPrivatBankDTO> actualExchangeRates = response.jsonPath()
                .getList("exchangeRate", ExchangeRatesPrivatBankDTO.class);

        List<String> expectedCurrencies = Arrays.asList(
                "AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP",
                "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD",
                "TMT", "TRY", "UAH", "USD", "UZS"
        );

        List<String> actualCurrencies = actualExchangeRates.stream()
                .map(ExchangeRatesPrivatBankDTO::getCurrency)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        for (String expectedCurrency : expectedCurrencies) {
            softAssertions.assertThat(actualCurrencies)
                    .as("Currency %s should be present in exchange rates", expectedCurrency)
                    .contains(expectedCurrency);
        }

        for (ExchangeRatesPrivatBankDTO rate : actualExchangeRates) {
            if (rate.getCurrency() != null) {
                softAssertions.assertThat(rate.getBaseCurrency())
                        .as("Base currency for %s", rate.getCurrency())
                        .isEqualTo("UAH");
            }
        }

        softAssertions.assertAll();
    }

    @Test
    public void verifyExchangeRatesValuesGreaterThanZero() {

        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("date", DATE)
                .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATES)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        List<ExchangeRatesPrivatBankDTO> rates =
                response.jsonPath().getList("exchangeRate", ExchangeRatesPrivatBankDTO.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < rates.size(); i++) {
            ExchangeRatesPrivatBankDTO rate = rates.get(i);
            String context = "exchangeRate[" + i + "] ";

            if (rate.getSaleRateNB() != null) {
                softAssertions.assertThat(rate.getSaleRateNB())
                        .as(context + "saleRateNB")
                        .isGreaterThan(0);
            }

            if (rate.getPurchaseRateNB() != null) {
                softAssertions.assertThat(rate.getPurchaseRateNB())
                        .as(context + "purchaseRateNB")
                        .isGreaterThan(0);
            }

            if (rate.getSaleRate() != null) {
                softAssertions.assertThat(rate.getSaleRate())
                        .as(context + "saleRate")
                        .isGreaterThan(0);
            }

            if (rate.getPurchaseRate() != null) {
                softAssertions.assertThat(rate.getPurchaseRate())
                        .as(context + "purchaseRate")
                        .isGreaterThan(0);
            }
        }

        softAssertions.assertAll();
    }

}


