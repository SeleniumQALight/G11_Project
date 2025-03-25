package org.apiTests;

import org.api.ApiHelperBookStore;
import org.api.BooksEndPoints;
import org.api.dto.responseDTO.UserBooksDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;
import static org.api.ApiHelper.responseSpecification;
import static org.data.TestData.*;

public class AddBookByApiTest {
  ApiHelperBookStore apiHelperBookStore = new ApiHelperBookStore();
  String token;
  String userId;

  @Before
  public void getTokenAndDeleteBooks() {
    token = ApiHelperBookStore.getToken(VALID_LOGIN_BOOK_API, VALID_PASSWORD_BOOK_API);
    userId = ApiHelperBookStore.getUserId(VALID_LOGIN_BOOK_API, VALID_PASSWORD_BOOK_API);
    apiHelperBookStore.deleteAllBooks(token, userId);
  }

  @After
  public void deleteBooks() {
    apiHelperBookStore.deleteAllBooks(token, userId);
  }

  @Test
  public void addBookToUserProfileByApiTest() {
    String isbn = apiHelperBookStore.getFirstBookIsbn(token);
    apiHelperBookStore.addBookToUser(token, userId, isbn);

    UserBooksDto response =
            given()
                    .spec(requestSpecification)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get(BooksEndPoints.GET_ALL_BOOKS_OF_USER, userId)
                    .then()
                    .spec(responseSpecification)
                    .extract().as(UserBooksDto.class);

    Assert.assertEquals(1, response.getBooks().size());
    Assert.assertEquals(isbn, response.getBooks().get(0).getIsbn());
  }
}