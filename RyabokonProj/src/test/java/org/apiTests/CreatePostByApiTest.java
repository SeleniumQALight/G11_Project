package org.apiTests;

import com.github.javafaker.Faker;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.requestDTO.CreatePostDTO;
import org.api.dto.responseDTO.AuthorDTO;
import org.api.dto.responseDTO.PostsDTO;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static dk.brics.automaton.StringUnionOperations.build;
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

    @Test
    public void createPostByApi() {
        int initialNumberOfPosts = getNumberOfPosts();

        CreatePostDTO createPostDtoBody = CreatePostDTO.builder()
                .title("Post From API Ryabokon")
                .body("Post Body From API Ryabokon" + faker.animal().name())
                .uniquePost("yes")
                .select1("One Person")
                .token(token)
                .build();

        String actualResponse =
                given()
                        .spec(ApiHelper.requestSpecification)
                        .body(createPostDtoBody)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .spec(ApiHelper.responseSpecification)
                                .extract().response().body().asString();

        Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);
        Assert.assertEquals("Message in response ", "Congrats.", actualResponse.replace("\"", ""));

        int newNumberOfPosts = getNumberOfPosts();

        Assert.assertEquals("Number of Posts Before  + 1 and after creating post",
                initialNumberOfPosts + 1, newNumberOfPosts);

        PostsDTO expectedPost = PostsDTO.builder()
                .title(createPostDtoBody.getTitle())
                .body(createPostDtoBody.getBody())
                .select(createPostDtoBody.getSelect1())
                .uniquePost(createPostDtoBody.getUniquePost())
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
                                .isEqualTo(expectedPost);

softAssertions.assertAll();
    }

    private int getNumberOfPosts() {
        return apiHelper.getAllPostsByUserRequest(TestData.VALID_LOGIN_API)
                .extract().body().as(PostsDTO[].class).length;
    }


}
