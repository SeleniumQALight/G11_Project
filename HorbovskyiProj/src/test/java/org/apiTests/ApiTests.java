package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.Endpoints;
import org.api.dto.responseDTO.AuthorDTO;
import org.api.dto.responseDTO.PostsDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {
        PostsDTO[] actualResponce =
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(Endpoints.POST_BY_USER, USER_NAME)
                .then()
                .log().all()
                .statusCode(200)
                .body("[0].title", equalTo("The second Default post"))
                .body("author.username", everyItem(equalTo(USER_NAME)))
        // method#2 DTO
                .extract().body().as(PostsDTO[].class);
        ;
        logger.info("Number of posts = " + actualResponce.length);
        logger.info("Title [0] = " + actualResponce[0].getTitle());
        logger.info("UserName = " + actualResponce[0].getAuthor().getUsername());
        logger.info(actualResponce[1]);

        for (int i = 0; i < actualResponce.length; i++) {
            Assert.assertEquals("UserName is not expected in post " + i,
                    USER_NAME, actualResponce[i].getAuthor().getUsername());

        }

        PostsDTO[] expectedResponse = {
                new PostsDTO("The second Default post",
                                        "This post was created automatically after cleaning the database",
                                        "All Users",
                                        "no",
                        false,
                        new AuthorDTO(USER_NAME)),
                new PostsDTO("The first Default post",
                                        "This post was created automatically after cleaning the database",
                                        "All Users",
                                        "no",
                        false,
                        new AuthorDTO(USER_NAME))
        };

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponce)
                        .usingRecursiveComparison()
                                .ignoringFields("id", "createdDate", "author.avatar")
                                        .isEqualTo(expectedResponse);

        softAssertions.assertAll();

    }
}
