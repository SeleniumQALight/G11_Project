package org.apiTests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.responseDTO.AuthorDTO;
import org.api.dto.responseDTO.PostsDTO;
import org.assertj.core.api.SoftAssertions;
import org.categories.SmokeTestsFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();

    @Test
    @Category(SmokeTestsFilter.class)
    public void getAllPostsByUser() {
        PostsDTO[] actualResponse =
        given()
                   .contentType(ContentType.JSON)
                   .log().all() //request log
                .filter(new AllureRestAssured())
                .when()
                   .get(EndPoints.POST_BY_USER, USER_NAME) // URL
                .then()
                   .log().all()  //response log
                   .statusCode(200)
        // method #1 RestAssured assert
                .body("[0].title", equalTo("The second Default post"))
                .body("author.username", everyItem(equalTo(USER_NAME)))
        // method #2 ATO
                .extract().body().as(PostsDTO[].class);
        ;
        logger.info("Number of posts = " + actualResponse.length);
        logger.info("Title [0] = " + actualResponse[0].getTitle());
        logger.info("UserName = " + actualResponse[0].getAuthor().getUsername());
        logger.info(actualResponse[1]);

        for (int i = 0; i < actualResponse.length; i++) {
            Assert.assertEquals("UserNames is not expected in post " + i,
                    USER_NAME,
                    actualResponse[i].getAuthor().getUsername()
            );

            PostsDTO[] expectedResponse = {
                    PostsDTO.builder()
                            .title("The second Default post")
                            .body("This post was created automatically after cleaning the database")
                            .uniquePost("no")
                            .select("All Users")
                            .isVisitorOwner(false)
                            .author(AuthorDTO.builder().username(USER_NAME).build())
                            .build(),
                    PostsDTO.builder()
                            .title("The first Default post")
                            .body("This post was created automatically after cleaning the database")
                            .uniquePost("no")
                            .select("All Users")
                            .isVisitorOwner(false)
                            .author(AuthorDTO.builder().username(USER_NAME).build())
                            .build()

//                    new PostsDTO("The second Default post"
//                            , "This post was created automatically after cleaning the database"
//                            ,"All Users" , "no", new AuthorDTO(USER_NAME), false),
//                    new PostsDTO("The first Default post"
//                            , "This post was created automatically after cleaning the database"
//                            ,"All Users" , "no", new AuthorDTO(USER_NAME), false)
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


    @Test
    public void getAllPostByUserNegative(){
        final  String NOT_VALID_USER_NAME = "NotValidUser";

        String actualResponse =
                apiHelper.getAllPostsByUserRequest(NOT_VALID_USER_NAME, 400)
                        // method #3 response as String
                        .extract().response().body().asString();


        Assert.assertEquals("Message in response ",
                "\"Sorry, invalid user requested. Wrong username - " + NOT_VALID_USER_NAME
                        + " or there is no posts. Exception is undefined\"",
                actualResponse);

    }

    @Test
    public void getAllPostByUserJsonPath(){
        //method #4 Json Path
        Response actualResponse =
                apiHelper.getAllPostsByUserRequest(USER_NAME, 200).extract().response();

        SoftAssertions softAssertions = new SoftAssertions();

        List<String> actualListOfTitle = actualResponse.jsonPath().getList("title", String.class);

        for (int i = 0; i < actualListOfTitle.size(); i++) {
            softAssertions.assertThat(actualListOfTitle.get(i))
                    .as("Item number " + i)
                    .contains("Default post");

        }

        List<Map> actualAuthorList = actualResponse.jsonPath().getList("author", Map.class);

        for(Map actualAuthorObject : actualAuthorList){
            softAssertions.assertThat(actualAuthorObject.get("username"))
                    .as("Field userName in Author ")
                    .isEqualTo(USER_NAME);
        }


        softAssertions.assertAll();
    }


    @Test
    public void getAllPostByUserSchemaValidation(){
        //method #5 Schema validation
        apiHelper.getAllPostsByUserRequest(USER_NAME, 200)
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"))
                ;
    }

}
