package org.apiTests;

import io.restassured.http.ContentType;
import org.api.EndPointsForPB;
import org.api.dto.responseDTO.ExchangePB_DTO;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;

public class RateAboveZeroPBTest {
    final String date = "22.03.2022";

    @Test
    public void testRateMoreThenZero() {

        ExchangePB_DTO actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPointsForPB.EXCHANGE_RATES_URL, date)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("exchangeRate.saleRateNB.findAll { it != null }", everyItem(greaterThan(0.0f)))
                        .body("exchangeRate.purchaseRateNB.findAll { it != null }", everyItem(greaterThan(0.0f)))
                        .body("exchangeRate.saleRate.findAll { it != null }", everyItem(greaterThan(0.0f)))
                        .body("exchangeRate.purchaseRate.findAll { it != null }", everyItem(greaterThan(0.0f)))
                        .extract().body().as(ExchangePB_DTO.class);

    }
}
