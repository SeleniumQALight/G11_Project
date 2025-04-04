package org.apiTests;

import com.github.javafaker.Faker;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.requestDTO.CreatePostDto;
import org.api.dto.responseDTO.AuthorDTO;
import org.api.dto.responseDTO.PostsDTO;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class CreatePostByApiTest {
  ApiHelper apiHelper = new ApiHelper();
  String token;
  Faker faker = new Faker();

  @Before
  public void getTokenAndDeletePosts() {
    token = apiHelper.getToken();
    apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, token);
  }

  @After
  public void deletePosts() {
    apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, token);
  }

  @Test
  public void createNewPostByAPI() {
    int initialNumberOfPosts = getNumberOfPosts();

    CreatePostDto createPostDto =
            CreatePostDto.builder()
                    .title("Post from API")
                    .body("Post body API " + faker.animal().name())
                    .uniquePost("yes")
                    .select1("One Person")
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

    Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);
    Assert.assertEquals("Message in response ", "Congrats.", actualResponse.replace("\"", ""));

    int newNumberOfPots = getNumberOfPosts();

    Assert.assertEquals("Number of posts Before + 1 and After creating post "
            , initialNumberOfPosts + 1, newNumberOfPots);

    PostsDTO exprctedPost =
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
            .ignoringFields("id", "createdDate", "author.avatar")
            .isEqualTo(exprctedPost);

    softAssertions.assertAll();
  }

  private int getNumberOfPosts() {
    return apiHelper.getAllPostsByUserRequest(TestData.VALID_LOGIN_API)
            .extract().response().as(PostsDTO[].class).length;
  }
}
