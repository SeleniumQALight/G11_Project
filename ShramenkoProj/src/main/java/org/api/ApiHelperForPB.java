package org.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ApiHelperForPB {

    public ValidatableResponse getExchangeRateForDatePB(String dateForExchangeRate, int expectedStatusCode){

        return given().queryParam("date", dateForExchangeRate)
                .contentType(ContentType.JSON) //підготовка, header
                .log().all()
                .when() //дія
                .get(EndPointsForPB.EXCHANGE_RATES_URL)
                .then() //перевірка
                .log().all()
                .statusCode(expectedStatusCode);
    }
}
