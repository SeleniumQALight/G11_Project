package org.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import netscape.javascript.JSObject;
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

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
//            .expectStatusCode(HttpStatus.SC_OK)
            .log(LogDetail.ALL)
            .build();

    public ValidatableResponse getPostsByUserRequest(String userName) {
        return getPostsByUserRequest(userName, HttpStatus.SC_OK);
    }

    public ValidatableResponse getPostsByUserRequest(String userName, int expectedStatusCode) {
        return given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when()
                .get(Endpoints.POST_BY_USER, userName)
                .then()
//                .log().all()
//                .statusCode(expectedStatusCode);
                .spec(responseSpecification.statusCode(expectedStatusCode));
    }

    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }



    public String getToken(String login, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", login);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(Endpoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().body().asString().replace("\"", "");


    }

    public void deleteAllPostsTillPresent(String username, String token) {
        PostsDTO[] listOfPosts = getPostsByUserRequest(username.toLowerCase()).extract().response().body().as(PostsDTO[].class);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info("Post with id " + listOfPosts[i].getId() + " was deleted");
            
        }
    }

    private void deletePostById(String token, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);

        given()
                .spec(requestSpecification)
                .body(bodyRequest)
                .when()
                .delete(Endpoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);
    }

    public void createPosts(Integer numberOfPosts, String token, Map<String, String> postsData) {
        for (int i = 0; i < numberOfPosts; i++) {
            CreatePostDTO bodyForPostCreation = CreatePostDTO.builder()
                    .title(postsData.get("title") + i)
                    .body(postsData.get("body") + i)
                    .select1(postsData.get("select"))
                    .uniquePost("no")
                    .token(token)
                    .build();
            given()
                    .spec(requestSpecification)
                    .body(bodyForPostCreation)
                    .when()
                    .post(Endpoints.CREATE_POST)
                    .then()
                    .spec(responseSpecification);

        }
    }
}
