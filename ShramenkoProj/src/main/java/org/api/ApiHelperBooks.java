package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.api.dto.responseDTO.*;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;

public class ApiHelperBooks {

    public final static String USER_NAME_BOOKS = "alla123";
    final static String PASSWORD_BOOKS = "kP_53LV*6vuCW7R";

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


    public ValidatableResponse getAllBooksByUserRequest(String token, String userId) {
        return getAllBooksByUserRequest(token, userId, 200);
    }

    public ValidatableResponse getAllBooksByUserRequest(String token,String userId, int expectedStatusCode) {
        return
                given()
                        .spec(requestSpecification)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get(EndPointsBooks.USER_WITH_ID,userId)
                        .then()
                        .spec(responseSpecification.statusCode(expectedStatusCode))
                ;
    }

    public ValidatableResponse getAllBooks() {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .get(EndPointsBooks.BOOKS)
                        .then()
                        .spec(responseSpecification.statusCode(200))
                ;
    }

    public String getStringFromLoginResponse(String userName, String password, String extract) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return
                given()
                        .spec(requestSpecification)
                        .body(requestBody.toMap())
                        .when()
                        .post(EndPointsBooks.LOGIN)
                        .then()
                        .spec(responseSpecification)
                        .extract().response()
                        .jsonPath().getString(extract);
    }

    public String getUserId() {
        return getStringFromLoginResponse(
                USER_NAME_BOOKS, PASSWORD_BOOKS, "userId");
    }

    public String getTokenBooks() {
        return getStringFromLoginResponse(
                USER_NAME_BOOKS, PASSWORD_BOOKS, "token");
    }

    public void deleteAllBooksByUser(String userId, String token) {
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(EndPointsBooks.BOOKS)
                .then()
                .spec(responseSpecification.statusCode(204))
                .extract().response().body().asString();
    }

    public UserDTO getListOfBooksByUserId(String token, String userId) {
        UserDTO listOfBooks = getAllBooksByUserRequest(token, userId)
                .extract().response().body().as(UserDTO.class);
        return listOfBooks;
    }
    public UserDTO getListOfBooks() {
        UserDTO listOfBooks = getAllBooks()
                .extract().response().body().as(UserDTO.class);
        return listOfBooks;
    }

    public void postNewBookInUserList(String token, String userId, String isbn){

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body("{\"userId\": \"" + userId + "\", \"collectionOfIsbns\": [{\"isbn\": \"" + isbn + "\"}]}")
                .when()
                .post(EndPointsBooks.BOOKS)
                .then()
                .spec(responseSpecification.statusCode(201));
    }



}
