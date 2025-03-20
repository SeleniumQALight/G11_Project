package org.apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.api.ApiHelper;
import org.api.Endpoints;
import org.api.dto.responseDTO.AuthorDTO;
import org.api.dto.responseDTO.PostsDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTests {
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();

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

        logger.info("Number of posts = " + actualResponce.length);
        logger.info("Title [0] = " + actualResponce[0].getTitle());
        logger.info("UserName = " + actualResponce[0].getAuthor().getUsername());
        logger.info(actualResponce[1]);

        for (int i = 0; i < actualResponce.length; i++) {
            Assert.assertEquals("UserName is not expected in post " + i,
                    USER_NAME, actualResponce[i].getAuthor().getUsername());

        }

        PostsDTO[] expectedResponse = {
                PostsDTO.builder()
                        .title("The second Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder().username(USER_NAME).build())
                        .build(),
                PostsDTO.builder()
                        .title("The first Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder().username(USER_NAME).build())
                        .build()
//                new PostsDTO("The second Default post",
//                                        "This post was created automatically after cleaning the database",
//                                        "All Users",
//                                        "no",
//                        false,
//                        new AuthorDTO(USER_NAME)),
//                new PostsDTO("The first Default post",
//                                        "This post was created automatically after cleaning the database",
//                                        "All Users",
//                                        "no",
//                        false,
//                        new AuthorDTO(USER_NAME))
        };

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponce)
                        .usingRecursiveComparison()
                                .ignoringFields("id", "createdDate", "author.avatar")
                                        .isEqualTo(expectedResponse);

        softAssertions.assertAll();

    }

    @Test
    public void getAllPostsByUserNegative() {
        final String NOT_VALID_USER_NAME = "NotValidUser";

        String actualResponce = apiHelper.getPostsByUserRequest(NOT_VALID_USER_NAME, 400).extract().response().body().asString();
        Assert.assertEquals("Message in response ", "\"Sorry, invalid user requested. Wrong username - " + NOT_VALID_USER_NAME + " or there is no posts. Exception is undefined\"", actualResponce);

    }

    @Test
    public void getAllPostsByUserJsonPath() {

        Response actualResponce =
                apiHelper.getPostsByUserRequest(USER_NAME, 200).extract().response();

        SoftAssertions softAssertions = new SoftAssertions();

        List<String> actualListOfTitle = actualResponce.jsonPath().getList("title", String.class);

        for (int i = 0; i < actualListOfTitle.size(); i++) {
            softAssertions.assertThat(actualListOfTitle.get(i))
                    .as("Item number " + i)
                    .contains("Default post");

        }

        List<Map> actualAuthorList = actualResponce.jsonPath().getList("author", Map.class);

        for (Map actualAuthorObject : actualAuthorList) {
            softAssertions.assertThat(actualAuthorObject.get("username"))
                    .as("Field username in Author ")
                    .isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserSchemaValidation() {
        apiHelper.getPostsByUserRequest(USER_NAME, 200)
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"))
        ;
    }
}
