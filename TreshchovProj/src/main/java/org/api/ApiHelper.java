package org.api;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.*;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.*;
import org.apache.hc.core5.http.HttpStatus;
import org.api.dto.requestDTO.CreatePostDto;
import org.api.dto.responseDTO.PostsDTO;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.data.TestData.VALID_LOGIN_API;
import static org.data.TestData.VALID_PASSWORD_API;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass().getName());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).addFilter(new AllureRestAssured())
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getAllPostsByUserRequest(String userName, int expectedStatusCode){
        return given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when().get(EndPoints.POST_BY_USER, userName).then()
//                .log().all().statusCode(expectedStatusCode)
                .spec(responseSpecification.statusCode(expectedStatusCode));
    }

    public ValidatableResponse getAllPostsByUserRequest(String userName) {
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }

        public String getToken() {
        return getToken(VALID_LOGIN_API, VALID_PASSWORD_API);
    }

    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);
        return given().spec(requestSpecification).body(requestBody.toMap())
                .when().post(EndPoints.LOGIN)
                .then().spec(responseSpecification).extract().response().body().asString().replace("\"", "");
    }


    public void deleteAllPostsTillPresent(String userName, String token) {
        PostsDTO[] posts = getAllPostsByUserRequest(userName.toLowerCase()).extract().response().body().as(PostsDTO[].class);
        for (int i = 0; i < posts.length; i++) {
            deletePostById(token,posts[i].getId());
            logger.info("Post with id " + posts[i].getId() + " and title "+posts[i].getTitle() + " was deleted");


        }
    }

    private void deletePostById(String token, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);
        given().spec(requestSpecification).body(bodyRequest)
                .when().delete(EndPoints.DELETE_POST, id)
                .then().spec(responseSpecification);
    }

    public void createPosts(Integer numberOfPosts,String token, Map<String, String> postsData) {
        for (int i = 0; i < numberOfPosts; i++) {
            CreatePostDto bodyForPostCreation = CreatePostDto.builder().
                    title(postsData.get("title")+i).
                    body(postsData.get("body")).
                    select1(postsData.get("select")).
                    uniquePost("no").
                    token(token).
                    build();

            given().spec(requestSpecification).body(bodyForPostCreation)
                    .when().post(EndPoints.CREATE_POST)
                    .then().spec(responseSpecification);

        }

    }
}
