package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.api.dto.responseDTO.PubInfoDTO;

import static io.restassured.RestAssured.*;

public class ApiHelperForPB {

    //реквекст специфікація
    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    //для респонса
    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();


    public ValidatableResponse getExchangeRateForDatePB(String dateForExchangeRate, int expectedStatusCode) {

        return given().queryParam("date", dateForExchangeRate)
                .contentType(ContentType.JSON) //підготовка, header
                .log().all()
                .when() //дія
                .get(EndPointsForPB.EXCHANGE_RATES_URL)
                .then() //перевірка
                .log().all()
                .statusCode(expectedStatusCode);
    }


    public PubInfoDTO[] getExchangeRatePB(String coursId) {
        return given()
                .spec(requestSpecification)
                .queryParam("json", "")
                .queryParam("exchange", "")
                .queryParam("coursid", coursId)
                .when()
                .get(EndPointsForPB.PUB_INFO)
                .then()
                .spec(responseSpecification)
                .extract().body().as(PubInfoDTO[].class);
    }
}
