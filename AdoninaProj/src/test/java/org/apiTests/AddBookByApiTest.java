package org.apiTests;

import org.api.ApiHelper;
import org.api.ApiHelperBookStore;
import org.api.BooksEndPoints;
import org.api.dto.responseDTO.UserBooksDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;


public class AddBookByApiTest {
  ApiHelperBookStore apiHelperBookStore = new ApiHelperBookStore();
  String token;
  String userId;

  @Before
  public void getTokenAndDeleteBooks() {
    token = apiHelperBookStore.getToken();
    userId = apiHelperBookStore.getUserId();
    apiHelperBookStore.deleteAllBooks(userId, token);
  }

  @After
  public void deleteBooks() {
    apiHelperBookStore.deleteAllBooks(userId, token);
    Assert.assertEquals("Books are not deleted", 0,
            given()
                    .spec(requestSpecification)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get(BooksEndPoints.GET_BOOKS_OF_USER, userId)
                    .then()
                    .spec(ApiHelper.responseSpecification)
                    .extract().response().body().as(UserBooksDto.class).getBooks().length);
  }

  @Test
  public void addBookToUserProfileByApiTest() {
    String isbn = apiHelperBookStore.getFirstBookIsbn(token);

    apiHelperBookStore.addBook(token, isbn, userId);


    UserBooksDto actualResponse = given()
            .spec(requestSpecification)
            .header("Authorization", "Bearer " + token)
            .when()
            .get(BooksEndPoints.GET_BOOKS_OF_USER, userId)
            .then()
            .spec(ApiHelper.responseSpecification)
            .extract().response().body().as(UserBooksDto.class);

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions
            .assertThat(actualResponse.getBooks().length).isEqualTo(1);
    softAssertions.assertThat(actualResponse.getBooks()[0].getIsbn()).isEqualTo(isbn);
    softAssertions.assertAll();
  }
}