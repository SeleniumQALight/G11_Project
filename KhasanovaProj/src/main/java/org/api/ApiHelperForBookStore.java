package org.api;
import org.apache.http.HttpStatus;
import org.api.dto.requestDTO.AddBookDTO;
import org.api.dto.requestDTO.IsbnDTO;
import org.api.dto.responseDTO.BooksForUserDTO;
import org.api.dto.responseDTO.BookStoreLoginDTO;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;
import static org.api.ApiHelper.responseSpecification;

public class ApiHelperForBookStore {

    public static BookStoreLoginDTO login(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);  // ключі точно як у JSON
        requestBody.put("password", password);


        return given()
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .post(BookStoreEndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().as(BookStoreLoginDTO.class);
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
                .delete(BookStoreEndPoints.ALL_BOOKS)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    public String getFirstBookIsbn (String token) {
        BooksForUserDTO response =
                given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(BookStoreEndPoints.ALL_BOOKS)
                .then()
                .spec(responseSpecification)
                .extract().as(BooksForUserDTO.class);

        return response.getBooks().get(0).getIsbn();

    }

    public void addBookToUser(String token, String userId, String isbn) {
        AddBookDTO requestBody = new AddBookDTO(userId, List.of(new IsbnDTO(isbn)));

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(BookStoreEndPoints.ALL_BOOKS)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED);
    }

}
