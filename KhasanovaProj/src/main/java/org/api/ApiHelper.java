package org.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.dto.requestDTO.CreatePostDTO;
import org.api.dto.responseDTO.PostsDTO;
import org.data.TestData;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());

    //Специфікації
    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())  //для аллюр репорту
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK) //статус код із бібліотеки
            .build();

    //винесення запиту в метод
    public ValidatableResponse getAllPostsByUserRequest(String userName, int expectedStatusCode) {
        return given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POST_BY_USER, userName)
                .then()
//                .log().all()
//                .statusCode(expectedStatusCode)
                .spec(responseSpecification.statusCode(expectedStatusCode)) //зміна статус коду із специфікації
                ;
    }

    public ValidatableResponse getAllPostsByUserRequest(String userName) {
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }
    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);  //ключі як у джейсоні

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().body().asString().replace("\"", "");
    }

    public void deleteAllPostsTillPresent(String userName, String token) {
        PostsDTO[] listOfPosts = getAllPostsByUserRequest(userName.toLowerCase())
                .extract().response().body().as(PostsDTO[].class);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(
                    String.format("Post with id %s and title '%s'",
                            listOfPosts[i].getId(), listOfPosts[i].getTitle())
            );
        }
    }

    private void deletePostById(String token, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);

        given()
                .spec(requestSpecification)
                .body(bodyRequest)
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);
    }

    public void createPosts(Integer numberOfPosts, String token, Map<String, String> postsData) {
        for (int i = 0; i < numberOfPosts; i++) {
            CreatePostDTO bodyOfPostCreation =
                    CreatePostDTO.builder()
                            .title(postsData.get("title") + " " + i) //значення з дататейбл
                            .body(postsData.get("body"))
                            .select1(postsData.get("select"))
                            .uniquePost("no")
                            .token(token)
                            .build();
            given()
                    .spec(requestSpecification)
                    .body(bodyOfPostCreation)
                    .when()
                    .post(EndPoints.CREATE_POST)
                    .then()
                    .spec(responseSpecification);
        }
    }
}
