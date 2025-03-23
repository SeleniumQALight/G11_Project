package org.api;

import io.restassured.response.ValidatableResponse;
import org.data.TestData;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;
import static org.api.ApiHelper.responseSpecification;

public class ApiHelperDemoqa {

    public String getToken() {
        return getLogin(TestData.VALID_LOGIN_API_DEMOQA, TestData.VALID_PASSWORD_API_DEMOQA).get("token").toString();
    }

    public String getUserId() {
        return getLogin(TestData.VALID_LOGIN_API_DEMOQA, TestData.VALID_PASSWORD_API_DEMOQA).get("userId").toString();
    }


    public Map getLogin(String login, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("password", password);
        requestBody.put("userName", login);


        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(DemoqaEndpoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().body().as(Map.class);

    }


    public void deleteAllBooks(String userID, String token) {
        given().queryParam("UserId", userID)
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(DemoqaEndpoints.DELETE_ALL_BOOKS)
                .then()
                .spec(responseSpecification)
                .statusCode(204);

    }

    public String getFirstBookIsbn (String token) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(DemoqaEndpoints.BOOKS)
                .then()
                .spec(responseSpecification)
                .extract().response().jsonPath().getString("books[0].isbn");
    }

    public void addBook (String token, String isbn, String userId) {
        JSONObject requestBody = new JSONObject();
        List<JSONObject> books = new ArrayList<>();
        JSONObject[] book = {new JSONObject()};
        books.add(book[0]);
        requestBody.put("userId", userId);
        requestBody.put("collectionOfIsbns", books);
        book[0].put("isbn", isbn)
        ;





        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toMap())
                .when()
                .post(DemoqaEndpoints.BOOKS)
                .then()
                .spec(responseSpecification)
        ;
    }

    public ValidatableResponse getBooksOfUser(String userId, String token) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(DemoqaEndpoints.GET_BOOKS_OF_USER, userId)
                .then()
                .spec(responseSpecification)
        ;
    }
}
