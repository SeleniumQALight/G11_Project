package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.EndPoints;
import org.api.dto.ApiHelper;
import org.api.dto.responseDTO.AuthorDTO;
import org.api.dto.responseDTO.PostsDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getALlPostsByUser(){
        PostsDTO[] actualResponse = //[] - для респонсів, які приходять з декількома об'єктами
        given() //підготовка перед дією
                  .contentType(ContentType.JSON)
                  .log().all()
                .when() //дія, виконання запиту
                  .get(EndPoints.POST_BY_USER, USER_NAME) //URL
                .then() //перевірка результату
                  .log().all()
                  .statusCode(200)
        // method #1 RestAssured assert
                .body("[0].title", equalTo("The second Default post"))  //для точкової перевірки json напряму
                .body("author.username", everyItem(equalTo(USER_NAME)))         //за допомогою бібліотеки CoreMatchers
        // method #2 DTO
                .extract().body().as(PostsDTO[].class)
        ;
        logger.info("Number of posts = " + actualResponse.length);
        logger.info("Title [0] = " + actualResponse[0].getTitle()); //вивід тайтлу першого поста
        logger.info("UserName = " + actualResponse[0].getAuthor().getUsername()); //вивід юзернейму з атвора першого поста
        logger.info(actualResponse[1]); //вивід всіх полів другого поста завдяки перевизначеному toString

        for (int i = 0; i < actualResponse.length; i++) {  //перевірка за допомогою Assert, всередину циклу можна додати кілька перевірок
            Assert.assertEquals("Username is not expected in post " + i,
                    USER_NAME,
                    actualResponse[i].getAuthor().getUsername());

        }

        PostsDTO[] expectedResponse = { //створення масиву з об'єктами
                PostsDTO.builder() //порядок філдів в білдері не має значення
                        .title("The second Default post")
                        .body("This post was created automatically after cleaning the database")
                        .uniquePost("no")
                        .select("All Users")
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder().username(USER_NAME).build())
                        .build(), //метод, який створює об'єкт

                PostsDTO.builder()
                        .title("The first Default post")
                        .body("This post was created automatically after cleaning the database")
                        .uniquePost("no")
                        .select("All Users")
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder().username(USER_NAME).build())
                        .build()


//                new PostsDTO("The second Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users", "no", new AuthorDTO(USER_NAME), false),
//                new PostsDTO("The first Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users", "no", new AuthorDTO(USER_NAME), false)
        };

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponse)
                        .usingRecursiveComparison() //порівняння усіх об'єктів в масиві
                          .ignoringFields("id", "createdDate", "author.avatar")
                           //ігнорування полів, назви з джави, а не джейсону
                             .isEqualTo(expectedResponse);

//        actualResponse.equals(expectedResponse); - гірший варіант порівняння, бо він впаде без уточнення,
//                                                    які поля не спвівпали + немає ігнорування полів

        softAssertions.assertAll();

    }


    @Test
    public void getAllPostsByUserNegative(){
        final String NOT_VALID_USER_NAME = "NotValidUser";

        String actualResponse =
                apiHelper.getAllPostsByUserRequest(NOT_VALID_USER_NAME, 400)
                        //method #3 response as String
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response ",
                "\"Sorry, invalid user requested. Wrong username - "+NOT_VALID_USER_NAME+" or there is no posts. Exception is undefined\"",
                actualResponse);
    }
}
