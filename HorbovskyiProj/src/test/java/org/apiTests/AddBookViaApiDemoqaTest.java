package org.apiTests;

import org.api.ApiHelper;
import org.api.ApiHelperDemoqa;
import org.api.DemoqaEndpoints;
import org.api.dto.responseDTO.DemoqaBooksByUserDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;

public class AddBookViaApiDemoqaTest {
    ApiHelperDemoqa apiHelperDemoqa = new ApiHelperDemoqa();
    String token;
    String userId;

    @Before
    public void getTokenAndDeleteBooks() {
        token = apiHelperDemoqa.getToken();
        userId = apiHelperDemoqa.getUserId();
        apiHelperDemoqa.deleteAllBooks(userId, token);

    }

    @After
    public void deleteBooks() {
        apiHelperDemoqa.deleteAllBooks(userId, token);
        Assert.assertEquals("Books are not deleted", 0,
                given()
                        .spec(requestSpecification)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get(DemoqaEndpoints.GET_BOOKS_OF_USER, userId)
                        .then()
                        .spec(ApiHelper.responseSpecification)
                        .extract().response().body().as(DemoqaBooksByUserDTO.class).getBooks().length);
    }

    @Test
    public void addBookViaApi() {
        String isbn = apiHelperDemoqa.getFirstBookIsbn(token);

        apiHelperDemoqa.addBook(token, isbn, userId);


        DemoqaBooksByUserDTO actualResponse = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(DemoqaEndpoints.GET_BOOKS_OF_USER, userId)
                .then()
                .spec(ApiHelper.responseSpecification)
                .extract().response().body().as(DemoqaBooksByUserDTO.class);


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualResponse.getBooks().length).isEqualTo(1);
        softAssertions.assertThat(actualResponse.getBooks()[0].getIsbn()).isEqualTo(isbn);
        softAssertions.assertAll();
    }

}
