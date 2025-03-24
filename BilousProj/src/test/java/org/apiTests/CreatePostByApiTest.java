package org.apiTests;

import com.github.javafaker.Faker;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dataTransferObject.requestDTO.CreatePostDTO;
import org.api.dataTransferObject.responseDTO.AuthorDTO;
import org.api.dataTransferObject.responseDTO.PostsDTO;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    ApiHelper apiHelper = new ApiHelper();
    String token;
    Faker faker = new Faker();

    @Before
    public void getTokenAndDeletePosts() {
        token = apiHelper.getToken();
//        System.out.println("Token: " + token); // for debug token
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, token);
    }
    @After
    public void deletePosts() {
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, token);
    }

    @Test
    public void createNewPostByApi() {
        int initialNumberOfPosts = getNumberOfPosts();

        CreatePostDTO createPostDTOBody = CreatePostDTO.builder()
                .title("Title Post from Api")
                .body("Body Post body from Api" + faker.animal().name())
                .uniquePost("yes")
                .select1("One Person")
                .token(token)
                .build();
        String actualResponse =
                given()
                        .spec(ApiHelper.requestSpecification)
                        .body(createPostDTOBody)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .spec(ApiHelper.responseSpecification)
                        .extract().response().asString();

        Assert.assertEquals("Message in response", "\"Congrats.\"", actualResponse);
        Assert.assertEquals("Message in response", "Congrats.", actualResponse.replace("\"", ""));

        int newNumberOfPosts = getNumberOfPosts();
        Assert.assertEquals("Number of posts Before+1 and After creating post",
                initialNumberOfPosts + 1, newNumberOfPosts);

        PostsDTO expectedPost = PostsDTO.builder()
                .title(createPostDTOBody.getTitle())
                .body(createPostDTOBody.getBody())
                .select(createPostDTOBody.getSelect1())
                .uniquePost(createPostDTOBody.getUniquePost())
                .isVisitorOwner(false)
                .author(AuthorDTO.builder()
                        .username(TestData.VALID_LOGIN_API)
                        .build())
                .build();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(apiHelper.getAllPostsByUserRequest(TestData.VALID_LOGIN_API)
                        .extract().response().body().as(PostsDTO[].class)[0])
                        .usingRecursiveComparison()
                        .ignoringFields("id", "createdDate", "author.avatar")
                        .isEqualTo(expectedPost);
        softAssertions.assertAll();
    }

    private int getNumberOfPosts() {
        return apiHelper.getAllPostsByUserRequest(TestData.VALID_LOGIN_API)
                .extract().response().as(PostsDTO[].class).length;
    }
}
