package org.apiTests;

import com.github.javafaker.Faker;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.request.CreatePostDto;
import org.api.dto.response.AuthorDTO;
import org.api.dto.response.PostsDTO;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static dk.brics.automaton.StringUnionOperations.build;
import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;
import static org.api.ApiHelper.responseSpecification;

public class CreatePostByApiTest {


    ApiHelper apiHelper = new ApiHelper();
    String token;
    Faker faker = new Faker();

    @Before
    public void getTokenAndDeletePosts() {
        token = apiHelper.getToken();
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API,token);

    }

    @After


    @Test
    public void createPostByApi() {
        int initialNumberOfPosts = getNumberOfPosts();

        CreatePostDto createPostDto = CreatePostDto.builder()
                .title("Post from API title")
                .body("Post from API body " + faker.chuckNorris().fact())
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();

        String actualResponse =
                given()
                        .spec(ApiHelper.requestSpecification)
                        .body(createPostDto)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .spec(ApiHelper.responseSpecification)
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response", "\"Congrats.\"", actualResponse);
        Assert.assertEquals("Message in response", "Congrats.", actualResponse.replace("\"", ""));

        int newNumberOfPosts = getNumberOfPosts();
        Assert.assertEquals("Number of posts Before + 1 and After creating post",
                initialNumberOfPosts + 1, newNumberOfPosts);


        PostsDTO expectedPost =
                PostsDTO.builder()
                        .title(createPostDto.getTitle())
                        .body(createPostDto.getBody())
                        .select(createPostDto.getSelect1())
                        .uniquePost(createPostDto.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder()
                                .username(TestData.VALID_LOGIN_API)
                                .build())
                        .build();


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(apiHelper.getAllPostsByUserRequest(TestData.VALID_LOGIN_API)
                        .extract().response().body().as(PostsDTO[].class)[0])
                .usingRecursiveComparison()
                .ignoringFields("_id", "createdDate", "author.avatar")
                .isEqualTo(expectedPost);

        softAssertions.assertAll();

    }

    private int getNumberOfPosts() {
        return apiHelper.getAllPostsByUserRequest(TestData.VALID_LOGIN_API)
                .extract().response().as(PostsDTO[].class).length;
    }

}
