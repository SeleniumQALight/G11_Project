package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.dto.responseDTO.PostsDTO;
import org.data.TestData;
import org.json.JSONObject;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {
Logger logger = Logger.getLogger(getClass());

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

    public ValidatableResponse getAllPostsByUserRequest(String userName){
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }
    public ValidatableResponse getAllPostsByUserRequest(String userName, int expectedStatusCode) {
        return given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POST_BY_USER, userName)
                .then()
//                .log().all()
//                .statusCode(expectedStatusCode)
                .spec(responseSpecification.statusCode(expectedStatusCode))
                ;
    }

    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password); //зробили боді для реквесту

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().body().asString().replace("\"", ""); //в кінці прибираємо лапки з токену
                // дістань. з респонсу. боді. у форматі стрінг. і заміни лапки на пустоту

    }

    public void deleteAllPostsTillPresent(String userName, String token) {
        PostsDTO[] listOfPosts = getAllPostsByUserRequest(userName.toLowerCase())
                .extract().response().body().as(PostsDTO[].class);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(
                    String.format("Post with id %s and title '%s' ",
                            listOfPosts[i].getId(), listOfPosts[i].getTitle())
            );
        }
    }

    private void deletePostById(String token, String id){
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
