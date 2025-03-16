package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.PrivatBankEndpoints;
import org.api.dto.responseDTO.PrivatBankExchangeRatesDTO;
import org.assertj.core.api.SoftAssertions;
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


        PrivatBankExchangeRatesDTO expectedResponse =
                new PrivatBankExchangeRatesDTO(
                        "22.03.2022",
                        "PB",
                        980,
                        "UAH"
                );

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResponse).usingRecursiveComparison().ignoringFields("exchangeRate").isEqualTo(expectedResponse);
        softAssertions.assertAll();
    }
}
