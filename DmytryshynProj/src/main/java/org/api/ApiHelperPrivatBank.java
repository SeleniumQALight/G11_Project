package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.api.dto.responseDTO.PubInfoPrivatBankDTO;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public PubInfoPrivatBankDTO[] getExchangeRatePB(String coursId) {
        return given()
                .spec(requestSpecification)
                .queryParam("json", "")
                .queryParam("exchange", "")
                .queryParam("coursid", coursId)
                .when()
                .get(EndPointsPrivatBank.PUB_INFO)
                .then()
                .spec(responseSpecification)
                .extract().body().as(PubInfoPrivatBankDTO[].class);
    }
}

