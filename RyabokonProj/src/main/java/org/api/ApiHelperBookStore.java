package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.api.dto.bookstoreDTO.AddBookRequest;
import org.api.dto.bookstoreDTO.BookISBN;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelperBookStore {

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .build();
    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK) // 200
            .build();

    public Map<String, String> getTokenBookStore (String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .post(EndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().body().jsonPath().getMap("");
    }

    public void deleteAllBooksForUser(String userId, String token) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", userId);

        given()
                .queryParam("UserId", userId)
                .header("Authorization", "Bearer " + token)
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .delete(BookEndPoints.OPERATIONS_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT));
    }

    public void deleteBookForUser(String userId, String isbn, String token) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", userId);
        requestBody.put("isbn", isbn);

        given()
                .header("Authorization", "Bearer " + token)
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .delete(BookEndPoints.OPERATIONS_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT));
    }



    public List<String> getUserBooks(String userId, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .spec(requestSpecification)
                .when()
                .get(BookEndPoints.USER_BOOKS, userId)
                .then()
                .spec(responseSpecification)
                .extract().body().jsonPath().getList("books.isbn");
    }
    //get first book from the list
    public String getFirstBookFromList(String userId, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .spec(requestSpecification)
                .when()
                .get(BookEndPoints.USER_BOOKS, userId)
                .then()
                .spec(responseSpecification)
                .extract().body().jsonPath().getString("books[0].isbn");
    }

    //add the first book from the list and add it to user and check if it is added

    public void addBookForUser(String userId, String isbn, String token) {
        BookISBN isbnDTO = BookISBN.builder()
                .isbn(isbn)
                .build();
        AddBookRequest addBookRequestDTO = AddBookRequest.builder()
                .userId(userId)
                .collectionOfIsbn(List.of(isbnDTO))
                .build();

        given()
                .header("Authorization", "Bearer " + token)
                .spec(requestSpecification)
                .body(addBookRequestDTO)
                .when()
                .post(BookEndPoints.OPERATIONS_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

}
