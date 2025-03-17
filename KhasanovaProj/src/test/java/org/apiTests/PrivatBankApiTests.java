package org.apiTests;

import io.restassured.http.ContentType;
import org.api.PrivatBankEndPoints;
import org.api.dto.responseDTO.ExchangeRateDTO;
import org.api.dto.responseDTO.ExchangeRatesDateDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PrivatBankApiTests {
    @Test
    public void getExchangeRatesByDate() {
        String date = "22.03.2022";
        ExchangeRatesDateDTO actualResponse =
                given()
                          .contentType(ContentType.JSON)
                          .queryParam("date", date)
                          .log().all()
                        .when()
                          .get(PrivatBankEndPoints.GET_EXCHANGE_RATES)
                        .then()
                          .log().all()
                          .statusCode(200)
                          .extract().body().as(ExchangeRatesDateDTO.class);

        List<ExchangeRateDTO> exchangeRates = List.of(
                new ExchangeRateDTO("UAH", "AUD"),
                new ExchangeRateDTO("UAH", "AZN"),
                new ExchangeRateDTO("UAH", "BYN"),
                new ExchangeRateDTO("UAH", "CAD"),
                new ExchangeRateDTO("UAH", "CHF"),
                new ExchangeRateDTO("UAH", "CNY"),
                new ExchangeRateDTO("UAH", "CZK"),
                new ExchangeRateDTO("UAH", "DKK"),
                new ExchangeRateDTO("UAH", "EUR"),
                new ExchangeRateDTO("UAH", "GBP"),
                new ExchangeRateDTO("UAH", "GEL"),
                new ExchangeRateDTO("UAH", "HUF"),
                new ExchangeRateDTO("UAH", "ILS"),
                new ExchangeRateDTO("UAH", "JPY"),
                new ExchangeRateDTO("UAH", "KZT"),
                new ExchangeRateDTO("UAH", "MDL"),
                new ExchangeRateDTO("UAH", "NOK"),
                new ExchangeRateDTO("UAH", "PLN"),
                new ExchangeRateDTO("UAH", "SEK"),
                new ExchangeRateDTO("UAH", "SGD"),
                new ExchangeRateDTO("UAH", "TMT"),
                new ExchangeRateDTO("UAH", "TRY"),
                new ExchangeRateDTO("UAH", "UAH"),
                new ExchangeRateDTO("UAH", "USD"),
                new ExchangeRateDTO("UAH", "UZS")
                );

        ExchangeRatesDateDTO expectedResponse = new ExchangeRatesDateDTO("22.03.2022", "PB", 980,
                "UAH", exchangeRates);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponse)
                        .usingRecursiveComparison()
                                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB",
                                        "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                                        .isEqualTo(expectedResponse);


        softAssertions.assertAll();

    }

    @Test
    public void validateExchangeRatesAreBiggerThanNull(){
        String date = "22.03.2022";
        ExchangeRatesDateDTO actualResponse =
                given()
                          .contentType(ContentType.JSON)
                          .queryParam("date", date)
                          .log().all()
                        .when()
                          .get(PrivatBankEndPoints.GET_EXCHANGE_RATES)
                        .then()
                          .log().all()
                          .statusCode(200)
                          .extract().body().as(ExchangeRatesDateDTO.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (ExchangeRateDTO rate : actualResponse.getExchangeRate()) {
            System.out.println("Checking the exchange rate for a currency: " + rate.getCurrency());

            if (rate.getSaleRateNB() != null) {
                System.out.println("saleRateNB: " + rate.getSaleRateNB());
                softAssertions.assertThat(rate.getSaleRateNB())
                        .as("saleRateNB for currency: " + rate.getCurrency())
                        .isGreaterThan(0);
            }

            if (rate.getPurchaseRateNB() != null) {
                System.out.println("purchaseRateNB: " + rate.getPurchaseRateNB());
                softAssertions.assertThat(rate.getPurchaseRateNB())
                        .as("purchaseRateNB for currency: " + rate.getCurrency())
                        .isGreaterThan(0);
            }

            if (rate.getSaleRate() != null) {
                System.out.println("saleRate: " + rate.getSaleRate());
                softAssertions.assertThat(rate.getSaleRate())
                        .as("saleRate for currency: " + rate.getCurrency())
                        .isGreaterThan(0);
            }

            if (rate.getPurchaseRate() != null) {
                System.out.println("purchaseRate: " + rate.getPurchaseRate());
                softAssertions.assertThat(rate.getPurchaseRate())
                        .as("purchaseRate for currency: " + rate.getCurrency())
                        .isGreaterThan(0);
            }
        }

        softAssertions.assertAll();
    }
}
