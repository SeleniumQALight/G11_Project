package org.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    public ValidatableResponse getPostsByUserRequest(String userName, int expectedStatusCode) {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(Endpoints.POST_BY_USER, userName)
                .then()
                .log().all()
                .statusCode(expectedStatusCode);
    }
}
