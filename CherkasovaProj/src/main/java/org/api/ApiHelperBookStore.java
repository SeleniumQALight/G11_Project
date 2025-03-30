package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.dto.requestDTO.AddBookDTO;
import org.api.dto.requestDTO.IsbnDTO;
import org.api.dto.requestDTO.LoginRequestDTO;
import org.api.dto.responseDTO.LoginResponseDTO;
import org.api.dto.responseDTO.UserBooksDTO;

import static io.restassured.RestAssured.given;

public class ApiHelperBookStore {
    private Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public LoginResponseDTO loginUser(String userName, String password) {
        logger.info("Logging in user: " + userName);
        LoginRequestDTO requestBody = LoginRequestDTO.builder()
                .userName(userName)
                .password(password)
                .build();

        LoginResponseDTO response =
                given()
                .spec(requestSpecification)
                .body(requestBody)
                .when()
                .post(EndPointsBookStore.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().as(LoginResponseDTO.class);

        logger.info("User logged in successfully: " + userName);
        return response;
    }

    public void deleteAllBooks(String userId, String token) {
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(EndPointsBookStore.BOOKS)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        logger.info("All books deleted for user: " + userId);
    }

    public UserBooksDTO getUserBooks(String userId, String token) {
        UserBooksDTO response =
                given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .get(EndPointsBookStore.USER_BOOKS, userId)
                .then()
                .spec(responseSpecification)
                .extract().as(UserBooksDTO.class);
        logger.info("Books fetched for user: " + userId);
        return response;
    }

    public String getFirstBookIsbn() {
        String isbn =
                given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsBookStore.BOOKS)
                .then()
                .spec(responseSpecification)
                .extract().jsonPath().getString("books[0].isbn");
        logger.info("First book ISBN fetched: " + isbn);
        return isbn;
    }

    public void addBookToUser(String userId, String token, String isbn) {
        AddBookDTO requestBody = AddBookDTO.builder()
                .userId(userId)
                .collectionOfIsbns(java.util.Collections.singletonList(new IsbnDTO(isbn)))
                .build();

        UserBooksDTO response =
                given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(EndPointsBookStore.BOOKS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(UserBooksDTO.class);
        boolean bookAdded = response.getBooks().stream().anyMatch(book -> book.getIsbn().equals(isbn));

        if (bookAdded) {
            logger.info("Book with ISBN " + isbn + " successfully added to user: " + userId);
        } else {
            logger.error("Failed to add book with ISBN " + isbn + " to user: " + userId);
        }
    }
}
