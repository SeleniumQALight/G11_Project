package org.api.BookStoreHW;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.api.EndPointBookStore;
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
        BookIsbnDTO isbnDTO = BookIsbnDTO.builder()
                .isbn(chooseNumberOfBook(numberOfBook))
                .build();
        AddBookRequestDTO addBookRequestDTO = AddBookRequestDTO.builder()
                .userId(userId)
                .collectionOfIsbns(List.of(isbnDTO))
                .build();

        given()
                .header("Authorization", "Bearer " + token)
                .spec(requestSpecification)
                .body(addBookRequestDTO)
                .when()
                .post(EndPointBookStore.ACTION_WITH_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_CREATED));
    }
    public BooksResponseDTO getUserBooks(String userId, String token) {
            return given()
                .header("Authorization", "Bearer " + token)
                .spec(requestSpecification)
                .when()
                .get(EndPointBookStore.BOOKS_FOR_USER, userId)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK))
                .extract().body().as(BooksResponseDTO.class);
    }

    public String chooseNumberOfBook(int numberBook) {
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
