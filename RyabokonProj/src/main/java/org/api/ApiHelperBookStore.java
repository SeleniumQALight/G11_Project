package org.api;

import org.apache.hc.core5.http.HttpStatus;
import org.api.dto.bookstoreDTO.*;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;
import static org.api.ApiHelper.responseSpecification;


public class ApiHelperBookStore {
    public static LoginResponseDTO login(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);


        return given()
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .post(BookEndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().as(LoginResponseDTO.class);

    }

    public static String getToken(String username, String password) {
        return login(username, password).getToken();
    }

    public static String getUserId(String username, String password) {
        return login(username, password).getUserId();
    }

    public void deleteAllBooksForUser(String token, String userId) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", userId);


        given()
                .queryParam("UserId", userId)
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(BookEndPoints.OPERATIONS_BOOKS)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    public BooksListForUser getAllBooks() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(BookEndPoints.OPERATIONS_BOOKS)
                .then()
                .spec(responseSpecification)
                .extract().as(BooksListForUser.class);
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

    public String getFirstBookFromListISBN(String token) {
        BooksListForUser booksList = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(BookEndPoints.OPERATIONS_BOOKS)
                .then()
                .spec(responseSpecification)
                .extract().as(BooksListForUser.class);

        return booksList.getBooks().get(0).getIsbn();
    }


    public void addBookForUser(String userId, String isbn, String token) {

        AddBookRequest requestBody = AddBookRequest.builder()
                .userId(userId)
                .collectionOfIsbns(List.of(BooksInfoDTO.builder().isbn(isbn).build()))
                        .build();

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(BookEndPoints.OPERATIONS_BOOKS)
                .then()
                .log().all()
                .statusCode(org.apache.http.HttpStatus.SC_CREATED);
    }
}
