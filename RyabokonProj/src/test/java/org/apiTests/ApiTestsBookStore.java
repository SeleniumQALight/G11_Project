package org.apiTests;

import org.api.ApiHelperBookStore;
import org.api.BookEndPoints;
import org.api.dto.bookstoreDTO.BooksListForUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;
import static org.api.ApiHelper.responseSpecification;
import static org.data.TestDataBookStore.VALID_LOGIN_API;
import static org.data.TestDataBookStore.VALID_PASSWORD_API;

public class ApiTestsBookStore {
   ApiHelperBookStore apiHelperBookStore = new ApiHelperBookStore();
   String token;
    String userId;
    int numberOfBook = 0;

@Before

    public void getTokenAndId() {
       token = ApiHelperBookStore.getToken(VALID_LOGIN_API, VALID_PASSWORD_API);
       apiHelperBookStore.deleteAllBooksForUser(token, userId);
    }
   @Test
   public void addBooksToUser() {

      apiHelperBookStore.getAllBooks();
      String isbn = apiHelperBookStore.getFirstBookFromListISBN(token);
        apiHelperBookStore.addBookForUser(token, userId, isbn);

        BooksListForUser response =
                given()
                        .spec(requestSpecification)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get(BookEndPoints.USER_BOOKS, userId)
                        .then()
                        .spec(responseSpecification)
                        .extract().as(BooksListForUser.class);

        Assert.assertEquals(1, response.getBooks().size());
        Assert.assertEquals(isbn, response.getBooks().get(0));

  }


}
