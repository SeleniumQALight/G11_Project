package org.apiTests;

import org.apache.log4j.Logger;
import org.api.ApiHelper;
import org.api.dto.responseDTO.AllBooksDTO;
import org.api.dto.responseDTO.BookUserDTO;
import org.api.dto.responseDTO.BooksLoginDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class AddBooksByApiTest {
    final String USER_NAME = "tmaksym";
    final String PASSWORD = "987456654789wW!";
    private Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void addBooksByApi(){
        BooksLoginDTO response = apiHelper.getTokenBooks(USER_NAME, PASSWORD).extract().body().as(BooksLoginDTO.class);;
        String token = "Bearer " + response.getToken();
        apiHelper.deleteAllBooks(response.getUserId(), token);
        AllBooksDTO bookCollection = apiHelper.getAllBooks().extract().body().as(AllBooksDTO.class);
        String bookIsbn = bookCollection.getBooks()[0].getIsbn();
        apiHelper.addBooksToUser(response.getUserId(), token, bookIsbn);
        BookUserDTO actualResult = apiHelper.getUserById(response.getUserId(), token).extract().body().as(BookUserDTO.class);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResult.getBooks()).usingRecursiveComparison().isEqualTo(bookCollection.getBooks()[0]);

        softAssertions.assertAll();



    }





}
