package org.api.BookStoreHW;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.EndPointBookStore;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelperBookStore {
    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .build();
    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK) // 200
            .build();

    public Map getTokenBookStore(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPointBookStore.UserId)
                .then()
                .spec(responseSpecification)
                .extract().response().body().as(Map.class);
    }
    public void deleteAllBooksForUser(String userId, String token) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", userId);

        given()
                .queryParam("UserId", userId)
                .header("Authorization", "Bearer " + token)
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .delete(EndPointBookStore.ACTION_WITH_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT));
    }

    public void addBookToUser(String userId, int numberOfBook, String token) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", userId);
        JSONArray collectionOfIsbns = new JSONArray();
        JSONObject isbnObject = new JSONObject();
        String isbn = chooseNumberOfBook(numberOfBook);
        isbnObject.put("Isbn", isbn);
        collectionOfIsbns.put(isbnObject);
        requestBody.put("collectionOfIsbns", collectionOfIsbns);

        given()
                .header("Authorization", "Bearer " + token)
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .post(EndPointBookStore.ACTION_WITH_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    private String chooseNumberOfBook(int numberBook) {
        BooksResponseDTO booksResponseDTO = getAllBooks();
        String bookIsbn = booksResponseDTO.getBooks().get(numberBook).getIsbn();
        System.out.println(bookIsbn + "isbn of book");
        return bookIsbn;
    }
    private BooksResponseDTO getAllBooks() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointBookStore.ACTION_WITH_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK))
                .extract().body().as(BooksResponseDTO.class);
    }
}
