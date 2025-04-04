package org.apiTests;

import com.github.javafaker.Faker;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.requestDTO.CreatePostDto;
import org.api.dto.responseDTO.AuthorDTO;
import org.api.dto.responseDTO.PostsDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.data.TestData.VALID_LOGIN_API;


public class CreatePostByApiTest {
    ApiHelper apiHelper = new ApiHelper();
    String token;
    Faker faker = new Faker();

    @Before
    public void getTokenAndDeletePosts() {
    token = apiHelper.getToken();
    apiHelper.deleteAllPostsTillPresent(VALID_LOGIN_API, token);

    }

    @Test
    public void createNewPostByApi() {
        int initialNumberOfPosts = getNumberOfPosts();

        CreatePostDto createPostDtoBody = CreatePostDto.builder()
                .title("TitleTestTreshchovFromApi")
                .body("Body123" + faker.chuckNorris().fact())
                .uniquePost("yes")
                .select1("One Person")
                .token(token)
                .build();
        String actualResponse =
                given().spec(ApiHelper.requestSpecification).body(createPostDtoBody)
                        .when().post(EndPoints.CREATE_POST)
                        .then().spec(ApiHelper.responseSpecification).extract().response().body().asString();
        Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);
        Assert.assertEquals("Message in response ", "Congrats.", actualResponse.replace("\"", ""));
        int newNumberOfPosts = getNumberOfPosts();
        Assert.assertEquals("Number of posts before+1 and after creating post ", initialNumberOfPosts + 1, newNumberOfPosts);

        PostsDTO expectedPost = PostsDTO.builder()
                .title(createPostDtoBody.getTitle())
                .body(createPostDtoBody.getBody())
                .select(createPostDtoBody.getSelect1())
                .uniquePost(createPostDtoBody.getUniquePost())
                .isVisitorOwner(false)
                .author(AuthorDTO.builder()
                        .username(VALID_LOGIN_API)
                        .build())
                .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(apiHelper.getAllPostsByUserRequest(VALID_LOGIN_API).extract().response().body().as(PostsDTO[].class)[0])
                .usingRecursiveComparison().ignoringFields("id", "createdDate", "author.avatar").isEqualTo(expectedPost);

        softAssertions.assertAll();
    }

    private int getNumberOfPosts() {
        return apiHelper.getAllPostsByUserRequest(VALID_LOGIN_API).extract().response().as(PostsDTO[].class).length;
    }
}
