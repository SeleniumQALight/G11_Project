package org.api;

import io.restassured.http.ContentType;
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

    public LoginResponseDTO loginUser(String userName, String password) {
        logger.info("Logging in user: " + userName);
        LoginRequestDTO requestBody = LoginRequestDTO.builder()
                .userName(userName)
                .password(password)
                .build();

        LoginResponseDTO response =
                given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(EndPointsBookStore.LOGIN)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(LoginResponseDTO.class);

        logger.info("User logged in successfully: " + userName);
        return response;
    }

    public void deleteAllBooks(String userId, String token) {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(EndPointsBookStore.DELETE_BOOKS.replace("{userId}", userId))
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        logger.info("All books deleted for user: " + userId);
    }

    public UserBooksDTO getUserBooks(String userId, String token) {
        UserBooksDTO response =
                given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPointsBookStore.USER_BOOKS.replace("{userId}", userId))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(UserBooksDTO.class);
        logger.info("Books fetched for user: " + userId);
        return response;
    }

    public String getFirstBookIsbn() {
        String isbn =
                given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPointsBookStore.GET_ALL_BOOKS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getString("books[0].isbn");
        logger.info("First book ISBN fetched: " + isbn);
        return isbn;
    }

    public void addBookToUser(String userId, String token, String isbn) {
        AddBookDTO requestBody = AddBookDTO.builder()
                .userId(userId)
                .collectionOfIsbns(java.util.Collections.singletonList(new IsbnDTO(isbn)))
                .build();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(EndPointsBookStore.ADD_BOOK)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        logger.info("Book with ISBN " + isbn + " added to user: " + userId);
    }
}
