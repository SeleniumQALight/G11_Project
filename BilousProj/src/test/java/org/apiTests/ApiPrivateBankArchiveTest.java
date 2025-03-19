package org.apiTests;


import io.restassured.http.ContentType;
import org.api.EndPointsPB;
import org.api.PrivatBankDTO.CurrencyResponseDTO;
import org.api.PrivatBankDTO.ExchangeRateDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiPrivateBankArchiveTest {
    @Test
    public void getCurrencyForDateTest() {
        CurrencyResponseDTO currencyResponseDTO =
        given()
                    .contentType(ContentType.JSON)
                    .queryParam("date", "22.02.2022")
                    .log().all()
                .when()
                    .get(EndPointsPB.EXCHANGE_RATES)
                .then()
                    .statusCode(200)
                    .log().all()
                .body("date", equalTo("22.02.2022"))
                .body("bank", equalTo("PB"))
                .body("baseCurrency", equalTo(980))
                .body("baseCurrencyLit", equalTo("UAH"))
                    .extract().body().as(CurrencyResponseDTO.class)
        ;
        ExchangeRateDTO[] exchangeRateExpected = {
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("AUD").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("AZN").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("BYN").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CAD").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CHF").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CNY").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CZK").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("DKK").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("EUR").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("GBP").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("GEL").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("HUF").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("ILS").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("JPY").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("KZT").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("MDL").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("NOK").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("PLN").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("SEK").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("SGD").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("TMT").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("TRY").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("UAH").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("USD").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("UZS").build()
        };
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(currencyResponseDTO.getExchangeRate())
                .usingRecursiveComparison()
                .ignoringFields("saleRateNB",
                        "purchaseRateNB", "saleRate", "purchaseRate", "date", "bank", "baseCurrency", "baseCurrencyLit")
                .isEqualTo(exchangeRateExpected);
        softAssertions.assertAll();
    }
    @Test
    public void validateExchangeRatesGreaterThanZeroTest() {
        CurrencyResponseDTO currencyResponseDTO =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("date", "22.02.2022")
                        .log().all()
                        .when()
                        .get(EndPointsPB.EXCHANGE_RATES)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().body().as(CurrencyResponseDTO.class)
                ;

        SoftAssertions softAssertions = new SoftAssertions();
        for (ExchangeRateDTO rate : currencyResponseDTO.getExchangeRate()) {
            softAssertions.assertThat(rate.getSaleRateNB()).isGreaterThan(0);
            softAssertions.assertThat(rate.getPurchaseRateNB()).isGreaterThan(0);
            if (rate.getSaleRate() != null) {
                softAssertions.assertThat(rate.getSaleRate()).isGreaterThan(0);
            }
            if (rate.getPurchaseRate() != null) {
                softAssertions.assertThat(rate.getPurchaseRate()).isGreaterThan(0);
            }
        }
        softAssertions.assertAll();
    }
}

