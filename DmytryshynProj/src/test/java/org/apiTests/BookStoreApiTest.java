package org.apiTests;

import org.api.ApiHelperBookStore;
import org.api.EndPointsBookStore;
import org.api.dto.responseDTO.UserBooksDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;
import static org.api.ApiHelper.responseSpecification;
import static org.data.TestDataBookStore.VALID_LOGIN_API;
import static org.data.TestDataBookStore.VALID_PASSWORD_API;

public class BookStoreApiTest {
    ApiHelperBookStore apiHelperForBookStore = new ApiHelperBookStore();
    String token;
    String userId;

    @Before
    public void getTokenAndDeleteBooks(){
        token = ApiHelperBookStore.getToken(VALID_LOGIN_API, VALID_PASSWORD_API);
        userId = ApiHelperBookStore.getUserId(VALID_LOGIN_API, VALID_PASSWORD_API);
        apiHelperForBookStore.deleteAllBooks(token, userId);
    }


    @After
    public void deleteBooks(){
        apiHelperForBookStore.deleteAllBooks(token, userId);
    }


    @Test
    public void addBookToUserProfileByApiTest(){
        String isbn = apiHelperForBookStore.getFirstBookIsbn(token);
        apiHelperForBookStore.addBookToUser(token, userId, isbn);

        UserBooksDTO response =
                given()
                        .spec(requestSpecification)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get(EndPointsBookStore.GET_ALL_BOOKS_OF_USER, userId)
                        .then()
                        .spec(responseSpecification)
                        .extract().as(UserBooksDTO.class);

        Assert.assertEquals(1, response.getBooks().size());
        Assert.assertEquals(isbn, response.getBooks().get(0).getIsbn());
    }
}
