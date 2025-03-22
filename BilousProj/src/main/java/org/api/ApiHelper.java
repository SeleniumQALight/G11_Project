package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.dataTransferObject.responseDTO.PostsDTO;
import org.data.TestData;
import org.json.JSONObject;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .build();
    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK) // 200
            .build();

    public ValidatableResponse getAllPostsByUserRequest(String userName) {
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }

    public ValidatableResponse getAllPostsByUserRequest(String userName, int expectedStatusCode) {
        return given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POST_BY_USER, userName) // URL
                .then()
                .spec(responseSpecification.statusCode(expectedStatusCode));
//                .log().all()
//                .statusCode(expectedStatusCode);
    }
    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }
    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().body().asString().replace("\"", "");
    }

    public void deleteAllPostsTillPresent(String userName, String token) {

        PostsDTO[] listOfPosts = getAllPostsByUserRequest(userName.toLowerCase()).extract().body().as(PostsDTO[].class);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info("Post with id: " + listOfPosts[i].getId() + " was deleted");
        }
    }

    private void deletePostById(String token, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);
        given()
                .spec(requestSpecification)
                .body(bodyRequest)
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);
    }
}
