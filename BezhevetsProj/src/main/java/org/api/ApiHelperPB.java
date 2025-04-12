package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.dto.responseDTO.PBCurrencyRatesDTO;

import static io.restassured.RestAssured.given;


public class ApiHelperPB {
    private Logger logger = Logger.getLogger(getClass());

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public PBCurrencyRatesDTO[] getCurrencyRates(String coursid) {
        PBCurrencyRatesDTO[] currencyRates =
                given()
                        .spec(requestSpecification)
                        .queryParam("json")
                        .queryParam("exchange")
                        .queryParam("coursid", coursid)
                        .when()
                        .get(PrivatBankEndpoints.CURRENCY_RATES)
                        .then()
                        .spec(responseSpecification)
                        .extract().as(PBCurrencyRatesDTO[].class);
        return currencyRates;
    }
}
