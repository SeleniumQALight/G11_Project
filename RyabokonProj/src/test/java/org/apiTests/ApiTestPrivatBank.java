package org.apiTests;

import io.restassured.http.ContentType;
import org.api.PrivateBankEndPoints;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiTestPrivatBank {

    @Test
    public void testPrivatBankApi() {

        String date = "22.03.2022";

        given()
                .contentType(ContentType.JSON)
                .queryParam("date", date)
                .log().all()
                .when()
                .get(PrivateBankEndPoints.CURRENCY_EXCHANGE)
                .then()
                .log().all()
                .statusCode(200)


                .body("date", equalTo(date))
        .body("bank", equalTo("PB"))
                .body("baseCurrency", equalTo(980))
        .body("baseCurrencyLit", equalTo("UAH"))
;

    }

}
