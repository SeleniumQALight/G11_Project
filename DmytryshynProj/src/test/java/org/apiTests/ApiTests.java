package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.EndPoints;
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
        PostsDTO[] actualResponse =
        given()
                .contentType(ContentType.JSON)
                .log().all()
              .when()
                .get(EndPoints.POST_BY_USER, USER_NAME) // URL
              .then()
                .log().all()
                .statusCode(200)
        // method #1 RestAssured assert
                .body("[0].title", equalTo("The second Default post"))
                .body("author.username", everyItem(equalTo(USER_NAME)))
        // method #2 DTO
                .extract().body().as(PostsDTO[].class);
        ;

        logger.info("Number of posts = " + actualResponse.length);
        logger.info("Title [0] = " + actualResponse[0].getTitle());
        logger.info("UserName = " + actualResponse[0].getAuthor().getUsername());
        logger.info(actualResponse[1]);

        for (int i = 0; i < actualResponse.length; i++) {
            Assert.assertEquals("UserName is not as expected in post " + i ,
                    USER_NAME,
                    actualResponse[i].getAuthor().getUsername()
            );
        }

        PostsDTO[] expectedResponse = {
                new PostsDTO("The second Default post"
                        , "This post was created automatically after cleaning the database"
                        ,"All Users" , "no", new AuthorDTO(USER_NAME), false),
                new PostsDTO("The first Default post"
                        , "This post was created automatically after cleaning the database"
                        ,"All Users" , "no", new AuthorDTO(USER_NAME), false)
        };

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponse)
                        .usingRecursiveComparison()
                                .ignoringFields("id", "createdDate", "author.avatar")
                                        .isEqualTo(expectedResponse);


        softAssertions.assertAll();

    }
}
