package org.apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.responseDTO.AuthorDTO;
import org.api.dto.responseDTO.PostsDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class apiTests {
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getAllPostsByUser(){
        PostsDTO[] actualResponse =
    given().
            contentType(ContentType.JSON).log().all().
            when().get(EndPoints.POST_BY_USER,USER_NAME).
            then().log().all().statusCode(200)
        //method #1 RestAssured assert
        .body("[0].title", equalTo("The second Default post"))
            .body("author.username", everyItem(equalTo(USER_NAME)))
    //method #2 DTO
            .extract().body().as(PostsDTO[].class)
    ;

        logger.info(actualResponse[1].toString());

        for (int i = 0; i < actualResponse.length; i++) {
            Assert.assertEquals("Username is not as expected", USER_NAME, actualResponse[i].getAuthor().getUsername());

        }

        PostsDTO[] expectedResponse = {
//                new PostsDTO("The second Default post", "This post was created automatically after cleaning the database", "All Users", "no", false, new AuthorDTO(USER_NAME)),
//                new PostsDTO("The first Default post", "This post was created automatically after cleaning the database", "All Users", "no", false, new AuthorDTO(USER_NAME))
                PostsDTO.builder().title("The second Default post").body("This post was created automatically after cleaning the database").select("All Users").uniquePost("no").isVisitorOwner(false).author(AuthorDTO.builder().username(USER_NAME).build()).build(),
                PostsDTO.builder().title("The first Default post").body("This post was created automatically after cleaning the database").select("All Users").uniquePost("no").isVisitorOwner(false).author(AuthorDTO.builder().username(USER_NAME).build()).build()

        };

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponse).usingRecursiveComparison().ignoringFields("id","createdDate", "author.avatar").isEqualTo(expectedResponse);

        softAssertions.assertAll();



    }

    @Test
    public void getAllPostsByUserNegative(){
        final String NOT_VALID_USER_NAME = "NotValidUser";

        String actualResponse = apiHelper.getAllPostsByUserReqeuest(NOT_VALID_USER_NAME, 400).extract().response().body().asString(); //method 3 asString

        Assert.assertEquals("Message in response ", "\"Sorry, invalid user requested. Wrong username - "+ NOT_VALID_USER_NAME+ " or there is no posts. Exception is undefined\"", actualResponse);


    }

    @Test
    public void getAllPostsByUserJsonPath(){
        //method 4 JsonPath
        Response actualResponse = apiHelper.getAllPostsByUserReqeuest(USER_NAME, 200).extract().response();
        SoftAssertions softAssertions = new SoftAssertions();

        List<String> actualListOfTitle = actualResponse.jsonPath().getList("title", String.class);

        for (int i = 0; i < actualListOfTitle.size() ; i++) {
            softAssertions.assertThat(actualListOfTitle.get(i)).as("Title number " + i).contains("Default post");

        }

        List<Map> actualAuthorList = actualResponse.jsonPath().getList("author", Map.class);

        for (Map actualAuthorObject : actualAuthorList){
            softAssertions.assertThat(actualAuthorObject.get("username")).as("Field userName in Author ").isEqualTo(USER_NAME);
        }


        softAssertions.assertAll();
    }


    @Test
    public void getAllPostsByUserSchemaValidation(){
        //method 5 Schema validation
        apiHelper.getAllPostsByUserReqeuest(USER_NAME, 200).assertThat().body(matchesJsonSchemaInClasspath("response.json"));
    }
}
