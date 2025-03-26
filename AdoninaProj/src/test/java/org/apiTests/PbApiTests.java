package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.dto.responseDTO.ExchangeRateDTO;
import org.api.dto.responseDTO.PrivatBankDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.api.PbEndPoints.EXCHANGE_RATE;
import static org.hamcrest.CoreMatchers.*;

public class PbApiTests {
  private final String DATE = "22.03.2022";
  private Logger logger = Logger.getLogger(getClass());

  @Test
  public void getValidFieldInResponse() {
    PrivatBankDTO actualResponse =
            given()
                    .contentType(ContentType.JSON)
                    .queryParam("date", DATE)
                    .log().all()
                    .when()
                    .get(EXCHANGE_RATE) //URL
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("date", equalTo(DATE))
                    .body("bank", equalTo("PB"))
                    .body("baseCurrency", equalTo(980))
                    .body("baseCurrencyLit", equalTo("UAH"))
                    .body("exchangeRate.baseCurrency", everyItem(equalTo("UAH")))
                    .body("exchangeRate.currency", hasItem(anyOf(equalTo("AUD"), equalTo("AZN"),
                            equalTo("BYN"), equalTo("CAD"), equalTo("CHF"), equalTo("CNY"),
                            equalTo("CZK"), equalTo("DKK"), equalTo("EUR"), equalTo("GBP"),
                            equalTo("GEL"), equalTo("HUF"), equalTo("ILS"), equalTo("JPY"),
                            equalTo("KZT"), equalTo("MDL"), equalTo("NOK"), equalTo("PLN"),
                            equalTo("SEK"), equalTo("SGD"), equalTo("TMT"), equalTo("TRY"),
                            equalTo("UAH"), equalTo("USD"), equalTo("UZS"))))

                    .extract().body().as(PrivatBankDTO.class);
    ;
    logger.info("Date = " + actualResponse.getDate());
    logger.info("Base currency = " + actualResponse.getBaseCurrencyLit());
    logger.info("Bank = " + actualResponse.getBank());
    logger.info("Number of currency rates: " + actualResponse.getExchangeRate().size());
    logger.info(actualResponse);

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
            .map(currency -> new ExchangeRateDTO("UAH", currency))
            .collect(Collectors.toList());

    PrivatBankDTO expectedResponse =
            new PrivatBankDTO(DATE
                    , "PB"
                    , 980, "UAH",
                    expectedExchangeRates);


    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions
            .assertThat(actualResponse)
            .usingRecursiveComparison()
            .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB",
                    "exchangeRate.saleRate", "exchangeRate.purchaseRate")
            .isEqualTo(expectedResponse);

    softAssertions.assertAll();
  }
}
