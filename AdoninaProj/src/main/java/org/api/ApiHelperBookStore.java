package org.api;

import org.apache.hc.core5.http.HttpStatus;
import org.api.dto.requestDTO.AddBookDto;
import org.api.dto.responseDTO.LoginResponseDto;
import org.api.dto.responseDTO.UserBooksDto;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ApiHelperBookStore {

  public static LoginResponseDto login(String userName, String password) {
    JSONObject requestBody = new JSONObject();
    requestBody.put("userName", userName);
    requestBody.put("password", password);


    return given()
            .spec(requestSpecification)
            .body(requestBody.toString())
            .when()
            .post(BooksEndPoints.LOGIN)
            .then()
            .spec(responseSpecification)
            .extract().as(LoginResponseDto.class);
  }

  public static String getToken(String username, String password) {
    return login(username, password).getToken();
  }

  public static String getUserId(String username, String password) {
    return login(username, password).getUserId();
  }

  public void deleteAllBooks(String token, String userId) {
    given().queryParam("UserId", userId)
            .spec(requestSpecification)
            .header("Authorization", "Bearer " + token)
            .when()
            .delete(BooksEndPoints.ALL_BOOKS)
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_NO_CONTENT);
  }

  public String getFirstBookIsbn(String token) {
    UserBooksDto response =
            given()
                    .spec(requestSpecification)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get(BooksEndPoints.ALL_BOOKS)
                    .then()
                    .spec(responseSpecification)
                    .extract().as(UserBooksDto.class);

    return response.getBooks().get(0).getIsbn();
  }

  public void addBookToUser(String token, String userId, String isbn) {
    AddBookDto requestBody = new AddBookDto(userId, List.of());

    given()
            .spec(requestSpecification)
            .header("Authorization", "Bearer " + token)
            .body(requestBody)
            .when()
            .post(BooksEndPoints.ALL_BOOKS)
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_CREATED);
  }
}
