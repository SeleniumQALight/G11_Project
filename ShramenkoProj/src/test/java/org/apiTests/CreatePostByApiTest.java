package org.apiTests;

import com.github.javafaker.Faker;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.requestDTO.CreatePostDTO;
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
    public void deletePostAfterTest() {
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, token);
    }

    @Test
    public void createNewPostByApi() {
        int initialNumberOfPosts = getNumberOfPosts(); //було постів ДО

        CreatePostDTO createPostDTOBody =
                CreatePostDTO.builder()
                        .title(faker.name().firstName())
                        .body("Post body from Api")
                        .select1("One Person")
                        .uniquePost("yes")
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
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response ",
                "\"Congrats.\"", actualResponse);


        int newNumberOfPosts = getNumberOfPosts(); //стало постів Після

        //перевірка, що у нашого користувача стало на 1 пост більше
        Assert.assertEquals("Number of posts Before+1 and After creating post ",
                initialNumberOfPosts + 1, newNumberOfPosts); // перевірка


        //а тепер перевірка того, що пост саме такий, як ми запрограмували:
        //спочатку очікуваний пост
        PostsDTO expectedPost =
                PostsDTO.builder()
                        .title(createPostDTOBody.getTitle())
                        .body(createPostDTOBody.getBody())
                        .select(createPostDTOBody.getSelect1())
                        .uniquePost(createPostDTOBody.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder()
                                .username(TestData.VALID_LOGIN_API)
                                .build())
                        .build();

        //перевірка
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
                .extract().response().as(PostsDTO[].class).length;
        //кількість постів цього юзера

    }
}
